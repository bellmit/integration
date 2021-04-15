package com.qunjie.crm.beans.results;

import com.google.common.base.MoreObjects;
import com.qunjie.crm.beans.EmployeeVO;

import java.util.Map;

/**
 * 封装获取公司所有人员列表结果 的JaveBean
 * 
 * @author huanghp
 * @date 2015年9月18日
 */
public class CorpUserMapResult extends BaseResult {

    private static final long serialVersionUID = 2273578171263471617L;

    /**
     * 人员总量
     */
    private int totalMember;

    /**
     * 部门总量
     */
    private int totalDepartment;

    /**
     * 人员列表 @see UserInfoResult
     */
    private Map<String, EmployeeVO> corpUserMap;

    public Map<String, EmployeeVO> getCorpUserMap() {
        return corpUserMap;
    }

    public void setCorpUserMap(Map<String, EmployeeVO> corpUserMap) {
        this.corpUserMap = corpUserMap;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public int getTotalDepartment() {
        return totalDepartment;
    }

    public void setTotalDepartment(int totalDepartment) {
        this.totalDepartment = totalDepartment;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("totalMember", totalMember)
                .add("totalDepartment", totalDepartment)
                .add("corpUserMap", corpUserMap)
                .toString();
    }

}
