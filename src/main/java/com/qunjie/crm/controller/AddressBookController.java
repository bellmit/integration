package com.qunjie.crm.controller;

import com.qunjie.crm.beans.results.*;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AddressBookManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/addressBook", produces = {"application/json", "application/xml"})
public class AddressBookController {

    private static final Logger LOG = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookManager addressBookManager;

    /**
     * 增加部门接口 输入部门信息后返回增加部门的结果
     */
    @RequestMapping(value = "dept/add", method = RequestMethod.POST)
    public DeptAddResult addDept(@RequestBody Department department) {
        DeptAddResult result;

        try {
            result = addressBookManager.addDept(department);
        } catch (AccessTokenException e) {
            LOG.error("addDept access token error, details:", e);
            result = new DeptAddResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public UserAddResult addDept(@RequestBody User arg) {
        UserAddResult result;

        try {
            result = addressBookManager.addUser(arg);
        } catch (AccessTokenException e) {
            LOG.error("addDept access token error, details:", e);
            result = new UserAddResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }



    /**
     * 修改部门信息 传入部门ID和部门名称以修改部门名称
     */
    @RequestMapping(value = "dept/modify", method = RequestMethod.POST)
    public DeptUpdateResult modifyDept(@RequestBody Department department) {
        DeptUpdateResult result;

        try {
            result = addressBookManager.modifyDept(department);
        } catch (AccessTokenException e) {
            LOG.error("modifyDept access token error, details:", e);
            result = new DeptUpdateResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }
    @RequestMapping(value = "user/modify", method = RequestMethod.POST)
    public UserUpdateResult modifyUser(@RequestBody User user) {
        UserUpdateResult result;

        try {
            result = addressBookManager.modifyUser(user);
        } catch (AccessTokenException e) {
            LOG.error("modifyDept access token error, details:", e);
            result = new UserUpdateResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }




    @RequestMapping(value = "user/getInfo/{openUserId}", method = RequestMethod.GET)
    public UserResult getUserInfo(@PathVariable String openUserId) {
        UserResult result = null;

        try {
            result = addressBookManager.getUserInfo(openUserId);
        } catch (AccessTokenException e) {
            LOG.error("getUserInfo access token error, details:", e);
            result = new UserResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "dept/list", method = RequestMethod.GET)
    public DeptListResult getDeptList() {
        DeptListResult result;

        try {
            result = addressBookManager.getDeptList();
        } catch (AccessTokenException e) {
            LOG.error("getDeptList access token error, details:", e);
            result = new DeptListResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "user/corpUserMap", method = RequestMethod.GET)
    public CorpUserMapResult getCorpUserMap() {
        CorpUserMapResult result;

        try {
            result = addressBookManager.getCorpEmployeeMap();
        } catch (AccessTokenException e) {
            LOG.error("getCorpUserMap access token error, details:", e);
            result = new CorpUserMapResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("getDeptDetail")
    public DeptDetailResult getDeptDetail(Integer departid) throws AccessTokenException {
        return addressBookManager.getDeptDetail(departid);
    }

}
