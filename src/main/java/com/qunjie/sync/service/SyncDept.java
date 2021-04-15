package com.qunjie.sync.service;

import com.qunjie.crm.beans.results.DepartDetail;
import com.qunjie.crm.beans.results.Department;
import com.qunjie.crm.beans.results.DeptAddResult;
import com.qunjie.crm.beans.results.DeptDetailResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AddressBookManager;
import com.qunjie.fanwei.webservice.dept.CompanyService;
import com.qunjie.fanwei.webservice.dept.Constant;
import com.qunjie.fanwei.webservice.dept.DeptService;
import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.param.DeptValueParam;
import com.qunjie.mysql.service.DeptValueService;
import com.qunjie.sync.model.CrmResponseCode;
import com.qunjie.sync.model.CrmStatus;
import com.qunjie.sync.model.MapKey;
import com.qunjie.sync.model.UpdParentidVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.service.SyncDept
 *
 * @author whs
 * Date:   2020/12/18  17:40
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Service
public class SyncDept {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UpdMapHandle updMapHandle;

    @Autowired
    private DeptValueService deptValueService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressBookManager addressBookManager;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 1.从泛微获取分部信息，
     * 2.遍历分部信息， 将遍历到的分部id放在List<Integer>中，
     * 从mysql查询该分部是否有映射：A.有映射: 查询映射数据，
     * 	a.如果无数据，做无映射处理，修改该映射消息;
     * 	b.直接修改；修改时，先挂在总公司下，更新其他数据，然后放入待更新list ； 调用修改接口
     * B.无映射：父级分部信息为空的挂在总公司下，不加入待更新list，父级分部不为空的暂挂在总公司下，同时放入待更新List(因为部分分部 父级分部尚未同步，可能在部门映射库里找不到父级部门)，调用添加接口 同时把id映射更新到mysql数据库
     * 3.更新待更新list(即父级部门映射)，分部数据同步完成;
     *
     *
     * 4.遍历List<Integer>.根据分部id查询泛微该分部下的部门list，将部门id放入deptList中
     * 查询mysql中该部门是否有映射:A.无映射: a.父级部门为空的则将本分部映射后的crm部门id作为父级部门，存入数据库，并将本部门的id映射添加到mysql数据库
     * 			           b.父级部门不为空，则将本部暂挂在当前映射分部下，添加映射到mysql数据库，同时把该条数据放在待更新List
     * 		            B有映射: 查询映射数据：a.有数据：直接更改，先将该部门挂在当前分部下，更新其他字段，放入待更新List
     * 					b.无数据:  删除该映射，做无映射处理
     * 更新待更新list，部门同步完成；
     *
     *
     * 5.遍历部门deptList，根据部门id查询该部门下的人，UserList;
     * 遍历UserList，映射用户部门，
     * 通过用户id在mysql表中查该用户的映射：
     * 	A.无映射:用户做添加操作(上级领导给个默认值 FSUID_62CD79E40D9AD1DF1D1E02F28986E039)，并将该用户映射添加进数据库中；并将用户添加到待更新List
     * 	B.有映射：	a.通过映射查询该用户，如果为空，删除该映射，做无映射处理
     * 		b.不为空，判断需不需要修改，需要修改，则修改(上级领导给默认值FSUID_62CD79E40D9AD1DF1D1E02F28986E039)，并将这条单据添加到待更新List
     * 更新待更新List，修改上级领导;人员同步结束；
     *
     * @return
     */
    public int syncDept(){
        //所有分部id 的list
        List<Integer> oaDeptIds = new ArrayList<>();
        //分部+部门的待更新数据
        List<UpdParentidVo> ParendIdNeedHandlelist = new ArrayList<>();
        List<CompanyService.Company> hrmDepartmentInfo = companyService.getHrmDepartmentInfo();
        List<CompanyService.Company> collect = hrmDepartmentInfo.stream().filter(e -> !(e.getCanceled() != null && e.getCanceled().equals("1"))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            return 1;
        }
        //同步分部数据
        collect.forEach(e->{
            oaDeptIds.add(Integer.valueOf(e.getSubcompanyid()));
            Department transfer = this.transfer(e);
            Integer oa_deptid = transfer.getId() + Constant.CUTNUM;
            DeptValueParam deptValueParam = new DeptValueParam();
            deptValueParam.setDeptid(oa_deptid);
            DeptValue byCondition = deptValueService.findByCondition(deptValueParam);
            if (null == byCondition){//无映射
               DeptValue deptValue = handleNoMappingCompany(transfer,oa_deptid,ParendIdNeedHandlelist);
               deptValueService.addDept(deptValue);
            }else {//有映射
                Integer departid = byCondition.getDepartid();
                try {
                    DeptDetailResult deptDetail = addressBookManager.getDeptDetail(departid);
                    if (deptDetail == null || deptDetail.getDepartment() == null ||
                            deptDetail.getDepartment().getDepartmentId() == 0){//crm无该部门数据,同步数据，并将原映射删除，判断父部门是否需要二次处理
                        DeptValue deptValue = handleNoMappingCompany(transfer,oa_deptid,ParendIdNeedHandlelist);
                        deptValueService.updDept(deptValue);
                    }else {//映射的部门不为空，判断是否需要修改//仅修改名称，一个分部门的父级部门一般不可能修改,不考虑
                        if (deptDetail.getDepartment().getIsStop()){
                            addressBookManager.CanceledDept(deptDetail.getDepartment().getDepartmentId(), CrmStatus.DEPT_RESTART);
                        }
                        if (needUpdate(transfer,deptDetail.getDepartment())) {
                            Department department = new Department();
                            DepartDetail departDetail = deptDetail.getDepartment();
                            department.setId(departDetail.getDepartmentId());
                            department.setName(transfer.getName());
                            addressBookManager.modifyDept(department);
                        }
                    }
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }

            }
        });
        //处理弃用部门
        hrmDepartmentInfo.removeAll(collect);
        handleCanceledCompanyData(hrmDepartmentInfo);

        //同步部门数据
        if (!CollectionUtils.isEmpty(oaDeptIds)){
            oaDeptIds.forEach(e->{
                List<DeptService.Deparment> hrmDeptInfo = deptService.getHrmDepartmentInfo(e);
                List<DeptService.Deparment> hrmDeptInfos = hrmDeptInfo.stream().filter(hrmDept -> !(hrmDept.getCanceled() != null && hrmDept.getCanceled().equals("1"))).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(hrmDeptInfos)){
                    DeptValueParam param = new DeptValueParam();
                    param.setDeptid(e + Constant.CUTNUM);
                    DeptValue byCondition = deptValueService.findByCondition(param);
                    if (null == byCondition){
                        log.info("此分部"+(e + Constant.CUTNUM)+"未同步，部门无法挂载!");
                        return;
                    }
                    hrmDeptInfos.forEach(k->{
                        if (k.getFullname().contains("解决")){
                            System.out.println("=========================");
                        }
                        System.out.println("部门==========================="+k);
                        DeptValueParam deptValueParam = new DeptValueParam();
                        deptValueParam.setDeptid(Integer.valueOf(k.getDepartmentid()));
                        DeptValue deptValue = deptValueService.findByCondition(deptValueParam);
                        if (null == deptValue){//部门无映射
                            DeptValue deptValue1 = handleNoMappingDept(k,byCondition,ParendIdNeedHandlelist);
                            deptValueService.addDept(deptValue1);
                        }else { //有映射
                            Integer departid = deptValue.getDepartid();
                            try {
                                DeptDetailResult deptDetail = addressBookManager.getDeptDetail(departid);
                                if (deptDetail == null || deptDetail.getDepartment() == null ||
                                        deptDetail.getDepartment().getDepartmentId() == 0){//映射数据为空
                                    DeptValue deptValue1 = handleNoMappingDept(k,byCondition,ParendIdNeedHandlelist);
                                    deptValueService.updDept(deptValue1);
                                }else {//映射数据不为空
                                    if (deptDetail.getDepartment().getIsStop()){
                                        addressBookManager.CanceledDept(deptDetail.getDepartment().getDepartmentId(), CrmStatus.DEPT_RESTART);
                                    }
                                    Department transfer = this.transfer(k,byCondition.getDepartid());
                                    if (!transfer.getName().equals(deptValue.getDeptnm())){
                                        deptValue.setDeptnm(transfer.getName());
                                        deptValueService.updDept(deptValue);
                                    }
                                    //无差别更新
                                        if (k.getSupdepartmentid() == null || Integer.valueOf(k.getSupdepartmentid()) == 0) {//父级部门为空
                                            transfer.setId(deptValue.getDepartid());
                                            addressBookManager.modifyDept(transfer);
                                        } else {//父级部门不为空
                                            transfer.setId(deptValue.getDepartid());
                                            addressBookManager.modifyDept(transfer);
                                            String supdepartmentid = k.getSupdepartmentid();
                                            UpdParentidVo updParentidVo = new UpdParentidVo();
                                            updParentidVo.setCrmId(String.valueOf(deptValue.getDepartid()));
                                            updParentidVo.setParentid(Integer.valueOf(supdepartmentid));
                                            ParendIdNeedHandlelist.add(updParentidVo);
                                        }
                                    //按需更新，不考虑子部门换父部门
//                                    if (needUpdate(transfer,deptDetail.getDepartment())){
//                                        Department department = new Department();
//                                        department.setId(deptDetail.getDepartment().getDepartmentId());
//                                        department.setName(transfer.getName());
//                                        addressBookManager.modifyDept(department);
//                                    }
                                }
                            } catch (AccessTokenException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
                //处理弃用部门
                hrmDeptInfo.removeAll(hrmDeptInfos);
                handleCanceledDeptData(hrmDeptInfo);
            });
        }
        updMapHandle.addMap(MapKey.DEPT,ParendIdNeedHandlelist);
        return 1;
    }

    //无映射处理-分部
    private DeptValue handleNoMappingCompany(Department transfer,Integer oa_deptid,List<UpdParentidVo> ParendIdNeedHandlelist){
        DeptValue deptValue = new DeptValue();
        if (transfer.getParentId() == null || transfer.getParentId() == 0){
            transfer.setParentId(Constant.SUPCOMPANY);
            try {
                DeptAddResult deptAddResult = addressBookManager.addDept(transfer);
                if (deptAddResult != null && deptAddResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE){
                    deptValue.setDeptid(oa_deptid);
                    deptValue.setDeptnm(transfer.getName());
                    deptValue.setDepartid(deptAddResult.getDepartmentId());
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }else {
            UpdParentidVo updParentidVo = new UpdParentidVo();
            updParentidVo.setParentid(transfer.getParentId() + Constant.CUTNUM);
            transfer.setParentId(Constant.SUPCOMPANY);
            try {
                DeptAddResult deptAddResult = addressBookManager.addDept(transfer);
                if (deptAddResult != null && deptAddResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE){
                    deptValue.setDeptid(oa_deptid);
                    deptValue.setDeptnm(transfer.getName());
                    deptValue.setDepartid(deptAddResult.getDepartmentId());
                    updParentidVo.setCrmId(String.valueOf(deptAddResult.getDepartmentId()));
                    ParendIdNeedHandlelist.add(updParentidVo);
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }
        return deptValue;
    }

    private DeptValue handleNoMappingDept(DeptService.Deparment k,DeptValue deptValue,List<UpdParentidVo> ParendIdNeedHandlelist){
        DeptValue deptValue1 = new DeptValue();
        if (k.getSupdepartmentid() == null || Integer.valueOf(k.getSupdepartmentid()) == 0){//父级部门为null
            Department transfer = this.transfer(k, deptValue.getDepartid());
            try {
                DeptAddResult deptAddResult = addressBookManager.addDept(transfer);
                if (deptAddResult != null && deptAddResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE){
                    deptValue1.setDeptid(transfer.getId());
                    deptValue1.setDeptnm(transfer.getName());
                    deptValue1.setDepartid(deptAddResult.getDepartmentId());
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }else {//父级部门不为空，先挂在当前分部下，然后把oa父级部门加入待修改list
            UpdParentidVo updParentidVo = new UpdParentidVo();
            Integer parentid = Integer.valueOf(k.getSupdepartmentid());
            updParentidVo.setParentid(parentid);
            Department transfer = this.transfer(k, deptValue.getDepartid());
            try {
                DeptAddResult deptAddResult = addressBookManager.addDept(transfer);
                if (null != deptAddResult && deptAddResult.getErrorCode() == CrmResponseCode.SUCCESS_CODE){
                    updParentidVo.setCrmId(String.valueOf(deptAddResult.getDepartmentId()));
                    deptValue1.setDeptid(transfer.getId());
                    deptValue1.setDeptnm(transfer.getName());
                    deptValue1.setDepartid(deptAddResult.getDepartmentId());
                    ParendIdNeedHandlelist.add(updParentidVo);
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }
        return deptValue1;
    }

    private boolean needUpdate(Department transfer, DepartDetail department) {
        if (null == transfer || null == department){
            return false;
        }
        return !transfer.getName().equals(department.getName());
    }

    private Department transfer(CompanyService.Company target){
        Department department = new Department();
        department.setId(Integer.valueOf(target.getSubcompanyid()));
        department.setName(target.getFullname());
        if (StringUtils.isNotEmpty(target.getShoworder())){
            department.setOrder((int) Double.valueOf(target.getShoworder()).doubleValue());
        }
        if (target.getSupsubcompanyid() != null) {
            Integer parendid = Integer.valueOf(target.getSupsubcompanyid());
            department.setParentId(parendid);
        }
        return department;
    }

    private Department transfer(DeptService.Deparment target,Integer parentid){
        Department department = new Department();
        department.setId(Integer.valueOf(target.getDepartmentid()));
        department.setName(target.getFullname());
        if (StringUtils.isNotEmpty(target.getShoworder()) && (int) Double.valueOf(target.getShoworder()).doubleValue() > 0) {
            department.setOrder((int) Double.valueOf(target.getShoworder()).doubleValue());
        }
        department.setParentId(parentid);
        return department;
    }

    public void handleCanceledCompanyData(List<CompanyService.Company> hrmDepartmentInfo){
        if (!CollectionUtils.isEmpty(hrmDepartmentInfo)){
            hrmDepartmentInfo.forEach(e->{
                DeptValue deptValue = deptValueService.findByCondition(Integer.valueOf(e.getSubcompanyid()));
                if (null != deptValue){
                    try {
                        addressBookManager.CanceledDept(deptValue.getDepartid(), CrmStatus.DEPT_CANCELED);
                    } catch (AccessTokenException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    public void handleCanceledDeptData(List<DeptService.Deparment> list){
        if (!CollectionUtils.isEmpty(list)){
            list.forEach(e->{
                DeptValue deptValue = deptValueService.findByCondition(Integer.valueOf(e.getDepartmentid()));
                if (null != deptValue){
                    try {
                        addressBookManager.CanceledDept(deptValue.getDepartid(), CrmStatus.DEPT_CANCELED);
                    } catch (AccessTokenException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }
}
