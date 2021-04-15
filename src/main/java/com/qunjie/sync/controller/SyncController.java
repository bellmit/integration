package com.qunjie.sync.controller;

import com.qunjie.sync.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.controller.SyncDeptController
 *
 * @author whs
 * Date:   2020/12/21  10:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    private SyncService syncService;

    @Autowired
    Environment environment;

    @GetMapping("/syncDeptAndUser")
    public int syncDeptAndUser(@RequestParam(required = false,value = "userids") String userids) throws IOException {
       return syncService.syncDeptAndUser(userids);
    }

    @GetMapping("/syncDept")
    public int syncDept(){
        return syncService.syncDept();
    }

    @GetMapping("/syncUser")
    public int syncUser(@RequestParam(required = false,value = "userids") String userids) throws IOException {
        return syncService.syncUser(userids);
    }

    @GetMapping("/getDepts")
    public String getDepts() throws IOException {
        return syncService.getDeptnms();
    }
}
