package com.qunjie.sync.service;

import com.qunjie.crm.beans.results.Department;
import com.qunjie.crm.beans.results.DeptUpdateResult;
import com.qunjie.crm.beans.results.User;
import com.qunjie.crm.beans.results.UserUpdateResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AddressBookManager;
import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.param.DeptValueParam;
import com.qunjie.mysql.param.UserValueParam;
import com.qunjie.mysql.service.DeptValueService;
import com.qunjie.mysql.service.UserValueService;
import com.qunjie.sync.model.CrmResponseCode;
import com.qunjie.sync.model.MapKey;
import com.qunjie.sync.model.UpdParentidVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.SyncHrm
 *
 * @author whs
 * Date:   2020/12/22  9:03
 * Description: 临时缓存类，防止父级部门尚未同步，出现父级部门同步失败
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class UpdMapHandle {

    @Autowired
    private DeptValueService deptValueService;
    @Autowired
    private UserValueService userValueService;
    @Autowired
    private AddressBookManager addressBookManager;

    private Map<String, List<UpdParentidVo>> updMap = new ConcurrentHashMap<>();

    // 乐观读写锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void handleUpdMap(){
        List<UpdParentidVo> updDepts = updMap.computeIfAbsent(MapKey.DEPT, e -> new ArrayList<>());
        List<UpdParentidVo> updUsers = updMap.computeIfAbsent(MapKey.USER, e -> new ArrayList<>());
        handleCompany(updDepts);
        handleUser(updUsers);
    }

    public void addMap(String key,List<UpdParentidVo> udpParentidVo){
        List<UpdParentidVo> updParentidVos = updMap.computeIfAbsent(key, e -> new ArrayList<>());
        lock.writeLock().lock();
        try {
            updParentidVos.addAll(udpParentidVo);
            updMap.put(key,updParentidVos);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void handleCompany(List<UpdParentidVo> updCompanys){
        if (CollectionUtils.isEmpty(updCompanys))
            return;

        updCompanys.forEach(e->{
            DeptValueParam deptValue = new DeptValueParam();
            deptValue.setDeptid(e.getParentid());
            DeptValue one = deptValueService.findByCondition(deptValue);
            if (null == one){
                log.info("=======crm部门id========"+e.getCrmId()+"===oa父级部门id====="+e.getParentid()+"=====此父级部门mysql中dept_value表中无映射");
            }else {
                Department department = new Department();
                department.setId(Integer.valueOf(e.getCrmId()));
                department.setParentId(one.getDepartid());
                try {
                    DeptUpdateResult deptUpdateResult = addressBookManager.modifyDept(department);
                    if (deptUpdateResult.getErrorCode() == 0){
                        log.info(department.toString()+"=====父节点更新成功");
                    }else {
                        log.info(department.toString()+"=====父节点更新失败");
                    }
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
            }
        });
        lock.writeLock().lock();
        try {
            updMap.remove(MapKey.DEPT);
        }finally {
            lock.writeLock().unlock();
        }
    }

    private void handleUser(List<UpdParentidVo> updCompanys){
        if (CollectionUtils.isEmpty(updCompanys))
            return;
        updCompanys.forEach(e->{
            UserValueParam valueParam = new UserValueParam();
            valueParam.setUserid(e.getParentid());
            UserValue userValue = userValueService.findByCondition(valueParam);
            if (null == userValue){
                log.info("=======crm人员id========"+e.getCrmId()+"===oa父级人员id====="+e.getParentid()+"=====此父级人员mysql中user_value表中无映射");
            }else {
                User user = new User();
                user.setOpenUserId(e.getCrmId());
                user.setAccount(e.getAccount());
                user.setLeaderId(userValue.getOpenuserid());
                try {
                    UserUpdateResult userUpdateResult = addressBookManager.modifyUser(user);
                    if (userUpdateResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE){
                        log.info(user.toString()+"=====父级领导更新成功");
                    }else {
                        log.info(user.toString()+"=====父级领导更新失败");
                    }
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
            }
        });
        lock.writeLock().lock();
        try {
            updMap.remove(MapKey.USER);
        } finally {
            lock.writeLock().unlock();
        }
    }


}
