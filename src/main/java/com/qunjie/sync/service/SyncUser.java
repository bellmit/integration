package com.qunjie.sync.service;

import com.qunjie.crm.beans.results.User;
import com.qunjie.crm.beans.results.UserAddResult;
import com.qunjie.crm.beans.results.UserResult;
import com.qunjie.crm.beans.results.UserUpdateResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AddressBookManager;
import com.qunjie.fanwei.webservice.user.UserService;
import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.service.DeptValueService;
import com.qunjie.mysql.service.UserValueService;
import com.qunjie.sync.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.service.SyncUser
 *
 * @author whs
 * Date:   2020/12/21  10:37
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class SyncUser {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookManager addressBookManager;
    @Autowired
    private UserValueService userValueService;
    @Autowired
    private DeptValueService deptValueService;
    @Autowired
    private UpdMapHandle updMapHandle;

    public static final String STR_EMPTY = "";

    public int syncUser(List<Integer> userids,List<String> deptnms) {
        List<UpdParentidVo> ParendIdNeedHandlelist = new ArrayList<>();
        List<UserService.User> hrmUserInfo = userService.getHrmUserInfo(STR_EMPTY, STR_EMPTY, STR_EMPTY, STR_EMPTY);
        log.info("hrmUserInfo.size() ================= " + hrmUserInfo.size());
        if (CollectionUtils.isEmpty(hrmUserInfo)) {
            return 1;
        }
        if (!CollectionUtils.isEmpty(userids)) {
            hrmUserInfo = hrmUserInfo.stream()
                    .filter(e -> StringUtils.isNotEmpty(e.getUserid()) && userids.contains(Integer.valueOf(e.getUserid())))
                    .distinct().collect(Collectors.toList());
        }
        if (!CollectionUtils.isEmpty(deptnms)){
            hrmUserInfo = hrmUserInfo.stream().filter(e->StringUtils.isNotEmpty(e.getDepartmentname()) && deptnms.contains(e.getDepartmentname()))
                    .distinct().collect(Collectors.toList());
        }
        //除去弃用的
        List<UserService.User> collect = hrmUserInfo.stream().filter(e -> !(e.getStatus() != null && Integer.valueOf(e.getStatus()) == 5)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            log.info("collect.size() ================= " + collect.size());
            collect.forEach(e -> {
                User user = this.transfer(e);
                UserValue userValue = userValueService.findByCondition(Integer.valueOf(e.getUserid()));
                if (null == userValue) {//无映射
                    try {
                        UserValue useV = NoMappingAdd(user, e, ParendIdNeedHandlelist);
                        userValueService.add(useV);
                    } catch (AccessTokenException ex) {
                        ex.printStackTrace();
                    }
                } else {//有映射
                    try {
                        UserResult userInfo = addressBookManager.getUserInfo(userValue.getOpenuserid());
                        if (null != userInfo && userInfo.getErrorCode() == CrmResponseCode.SUCCESS_CODE) {    //映射的数据无问题，根据需求进行修改
                            if (userInfo.isStop()) {
                                addressBookManager.CanceledUser(userValue.getOpenuserid(), CrmStatus.DEPT_RESTART);
                            }
                            if (NeedUpdate(user, e, userInfo)) {
                                user.setOpenUserId(userInfo.getOpenUserId());
                                UserUpdateResult userUpdateResult = addressBookManager.modifyUser(user);
                                int i = 1;
                                String name = user.getName();
                                while (null != userUpdateResult && userUpdateResult.getErrorCode() == CrmResponseCode.NAMEREPEAT_CODE){
                                    user.setName(name+i);
                                    i++;
                                    userUpdateResult = addressBookManager.modifyUser(user);
                                }
                                this.addUpdParentidVo(user,e,userInfo.getOpenUserId(),ParendIdNeedHandlelist);
                            }
                            if(e.getMobile() != null && !e.getMobile().equals(userValue.getMobile())){
                                userValue.setMobile(e.getMobile());
                                userValueService.update(userValue);
                            }
                        } else {
                            UserValue userV = NoMappingAdd(user, e, ParendIdNeedHandlelist);
                            userValueService.update(userV);
                        }
                    } catch (AccessTokenException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        hrmUserInfo.removeAll(collect);
        handleCanceledUser(hrmUserInfo);

        updMapHandle.addMap(MapKey.USER, ParendIdNeedHandlelist);
        return 1;
    }

    private void handleCanceledUser(List<UserService.User> hrmUserInfo) {
        if (CollectionUtils.isEmpty(hrmUserInfo)) {
            return;
        }
        hrmUserInfo.forEach(e -> {
            UserValue userValue = userValueService.findByCondition(Integer.valueOf(e.getUserid()));
            if (userValue != null) {
                try {
                    addressBookManager.CanceledUser(userValue.getOpenuserid(), CrmStatus.DEPT_CANCELED);
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private boolean NeedUpdate(User user, UserService.User e, UserResult userInfo) {
        if (user.getMobile() != null && !user.getMobile().equals(userInfo.getMobile())) {
            return true;
        }
        if (user.getName() != null && !user.getName().equals(userInfo.getName())) {
            return true;
        }
        if (user.getEmail() != null && !user.getEmail().equals(userInfo.getEmail())) {
            return true;
        }
        List<Integer> departmentIds = userInfo.getDepartmentIds();
        if (user.getMainDepartmentId() == null || CollectionUtils.isEmpty(departmentIds) || user.getMainDepartmentId().intValue() != departmentIds.get(departmentIds.size() - 1).intValue()) {
            return true;
        }
        //由于获取CRM用户信息中无直系领导id信息，故无法判断直系领导是否有改动,故全修改
        return true;
    }

    private UserValue NoMappingAdd(User user, UserService.User e, List<UpdParentidVo> ParendIdNeedHandlelist) throws AccessTokenException {
        UserValue userValueAdd = new UserValue();
        UserAddResult userAddResult = addressBookManager.addUser(user);
        int i = 1;
        String name = user.getName();
        while (null != userAddResult && userAddResult.getErrorCode() == CrmResponseCode.NAMEREPEAT_CODE){
            user.setName(name+i);
            i++;
            userAddResult = addressBookManager.addUser(user);
        }
        if (null != userAddResult && userAddResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE) {
            userValueAdd.setUserid(Integer.valueOf(e.getUserid()));
            userValueAdd.setUsernm(user.getName());
            userValueAdd.setOpenuserid(userAddResult.getOpenUserId());
            userValueAdd.setMobile(user.getMobile());
            this.addUpdParentidVo(user,e,userAddResult.getOpenUserId(),ParendIdNeedHandlelist);
        }
        return userValueAdd;
    }

    private void addUpdParentidVo(User user,UserService.User e,String OpenUserid,List<UpdParentidVo> ParendIdNeedHandlelist){
        if (e.getManagerid() != null && !e.getManagerid().equals("0") && StringUtils.isNotEmpty(user.getAccount())) {
            UpdParentidVo updParentidVo = new UpdParentidVo();
            updParentidVo.setCrmId(OpenUserid);
            updParentidVo.setAccount(user.getAccount());
            updParentidVo.setParentid(Integer.valueOf(e.getManagerid()));
            ParendIdNeedHandlelist.add(updParentidVo);
        }
    }

    private User transfer(UserService.User oaUser) {
        User user = new User();
        user.setMobile(oaUser.getMobile());
        user.setOpenUserId(oaUser.getUserid());
        user.setGender(SexEnum.valuesOf(oaUser.getSex()));
        DeptValue deptValue = deptValueService.findByCondition(Integer.valueOf(oaUser.getDepartmentid()));
        if (null != deptValue) {
            user.setMainDepartmentId(deptValue.getDepartid());
        }
        if (StringUtils.isNotEmpty(oaUser.getEmail())) {
            user.setEmail(oaUser.getEmail());
        }
        user.setName(oaUser.getLastname());
        user.setFullName(oaUser.getLastname());
        user.setAccount(oaUser.getLoginid());
        return user;
    }

}
