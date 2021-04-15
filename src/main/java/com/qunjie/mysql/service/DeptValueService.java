package com.qunjie.mysql.service;

import com.qunjie.mysql.mapper.DeptValueMapper;
import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.param.DeptValueParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.service.DeptValueService
 *
 * @author whs
 * Date:   2020/12/21  17:00
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class DeptValueService {
    @Autowired
    private DeptValueMapper deptValueMapper;

    public int addDept(DeptValue deptValue){
        if (deptValue == null || deptValue.getDeptid() == null || deptValue.getDepartid() == null){
            return 1;
        }
        return deptValueMapper.insertDept(deptValue);
    }

    public int updDept(DeptValue deptValue){
        return deptValueMapper.updDept(deptValue);
    }

    public int updDept(Integer deptid,Integer departid){
        DeptValue updDeptValue = new DeptValue();
        updDeptValue.setDeptid(deptid);
        updDeptValue.setDepartid(departid);
        return deptValueMapper.updDept(updDeptValue);
    }

    public DeptValue findByCondition(DeptValueParam deptValueParam){
        return deptValueMapper.findByCondition(deptValueParam);
    }
    public DeptValue findByCondition(Integer oadeptid){
        DeptValueParam deptValueParam = new DeptValueParam();
        deptValueParam.setDeptid(oadeptid);
        return deptValueMapper.findByCondition(deptValueParam);
    }

    /**
     * 查询crm部门id
     */
    public Integer queryCrmDeptIdByDeptNm(String deptnm){
        DeptValueParam deptValueParam = new DeptValueParam();
        deptValueParam.setDeptnm(deptnm);
        DeptValue byCondition = deptValueMapper.findByCondition(deptValueParam);
        if (byCondition != null){
            return byCondition.getDepartid();
        }
        return null;
    }

    public void delete(Integer oa_deptid) {
        deptValueMapper.delete(oa_deptid);
    }
}
