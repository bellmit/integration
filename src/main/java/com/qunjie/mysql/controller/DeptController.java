package com.qunjie.mysql.controller;

import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.param.DeptValueParam;
import com.qunjie.mysql.service.DeptValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.controller.DeptController
 *
 * @author whs
 * Date:   2020/12/21  17:00
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/mysql")
public class DeptController {

    @Autowired
    private DeptValueService deptValueService;
    @PostMapping("addDept")
    public int add(@RequestBody DeptValue deptValue){
        return deptValueService.addDept(deptValue);
    }

    @PostMapping("updDept")
    public int upd(@RequestBody DeptValue deptValue){
        return deptValueService.updDept(deptValue);
    }

    @GetMapping("readOne")
    public DeptValue find(DeptValueParam deptValueParam){
        DeptValue byCondition = deptValueService.findByCondition(deptValueParam);
        System.out.println(byCondition);
        return byCondition;
    }
}
