package com.qunjie.crm.manager;

import com.qunjie.crm.attendance.args.AttendanceQueryArg;
import com.qunjie.crm.attendance.result.AttendanceQueryResult;
import com.qunjie.crm.beans.results.*;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.results.QueryResult;

/**
 * 通讯录数据的管理接口
 * 
 * @author huanghp
 * @date 2015年9月18日
 */
public interface AddressBookManager {

    /**
     * 获取公司所有人员信息的方法
     * 
     * @return
     */
    public CorpUserMapResult getCorpEmployeeMap() throws AccessTokenException;

    /**
     * 添加部门
     * 
     * @param department 部门信息
     * @return
     * @throws AccessTokenException
     */
    public DeptAddResult addDept(Department department) throws AccessTokenException;

    /**
     * 修改部门
     * 
     * @param department 部门信息
     * @return
     * @throws AccessTokenException
     */
    public DeptUpdateResult modifyDept(Department department) throws AccessTokenException;

    /**
     * 修改部门状态
     * @param
     * @return
     * @throws AccessTokenException
     */
    public DeptUpdateResult CanceledDept(Integer deptid, Integer status) throws AccessTokenException;

    /**
     * 获取部门列表
     *
     * @return
     * @throws AccessTokenException
     */
    public DeptListResult getDeptList() throws AccessTokenException;

    /**
     * 查询部门详细信息
     * @param departid
     * @return
     * @throws AccessTokenException
     */
    public DeptDetailResult getDeptDetail(Integer departid) throws AccessTokenException;

    /**
     * 获取成员详细信息
     *
     * @param openUserId
     * @return
     * @throws AccessTokenException
     */
    public UserResult getUserInfo(String openUserId) throws AccessTokenException;

    /**
     * 添加用户
     *
     * @param user
     * @return
     * @throws AccessTokenException
     */
    public UserAddResult addUser(User user) throws AccessTokenException;

    /**
     * 修改用户
     * @param user
     * @return
     * @throws AccessTokenException
     */
    public UserUpdateResult modifyUser(User user) throws AccessTokenException;

    /**
     * 修改用户状态
     * @param userid
     * @param status
     * @return
     * @throws AccessTokenException
     */
    public UserUpdateResult CanceledUser(String userid, Integer status) throws AccessTokenException;

    /**
     * CRM对象接口V2查询
     * @param queryData
     * @return
     * @throws AccessTokenException
     */
    public QueryResult queryData(QueryData queryData) throws AccessTokenException;

    public QueryResult queryCustomData(QueryData queryData) throws AccessTokenException;

    public AttendanceQueryResult queryAttendance(AttendanceQueryArg queryArg,String uri) throws AccessTokenException;

}
