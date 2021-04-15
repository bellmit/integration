package com.qunjie.crm.attendance.controller;

import com.qunjie.crm.attendance.args.AttendanceModel;
import com.qunjie.crm.attendance.service.AttendanceService;
import com.qunjie.crm.exception.AccessTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.attendance.controller.AttendanceController
 *
 * @author whs
 * Date:   2021/2/20  10:59
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/attendance")
public class AttendanceController {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    AttendanceService attendanceService;
    @GetMapping("/query")
    public List<AttendanceModel> queryAttendance(@RequestParam("start") String start,@RequestParam("end") String end,@RequestParam("url") String url) throws AccessTokenException, ParseException {
        Date startD = sdf.parse(start);
        Date endD = sdf.parse(end);
        return attendanceService.queryAttendance(endD,startD,url);
    }

    @GetMapping("queryYestoday")
    public List<AttendanceModel> queryYestoday(String url) throws AccessTokenException {
        return attendanceService.query(url);
    }

    @GetMapping("/add")
    public void add() throws AccessTokenException {
        attendanceService.addAttendanceMysql();
    }

    @GetMapping("addDay")
    public void addDay(@RequestParam(value = "start",required = true) String start, @RequestParam(value = "end",required = true) String end,@RequestParam("url") String url) throws ParseException, AccessTokenException {
        Date startD = sdf.parse(start);
        Date endD = sdf.parse(end);
        attendanceService.addAttendanceMysql(endD,startD,url);
    }

}
