package com.qunjie.crm.attendance.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qunjie.crm.attendance.args.AttendanceModel;
import com.qunjie.crm.attendance.args.AttendanceQueryArg;
import com.qunjie.crm.attendance.result.AttendanceQueryResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.AddressBookManagerImpl;
import com.qunjie.mysql.mapper.CrmAttendanceMapper;
import com.qunjie.mysql.mapper.UserValueMapper;
import com.qunjie.mysql.model.CrmAttendance;
import com.qunjie.mysql.model.UserValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.attendance.service.AttendanceService
 *
 * @author whs
 * Date:   2021/2/20  10:35
 * Description: crm打卡数据同步到数据库，以便oa系统做同步任务
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class AttendanceService {

    @Autowired
    AddressBookManagerImpl addressBookManager;
    @Autowired
    UserValueMapper userValueMapper;
    @Autowired
    CrmAttendanceMapper crmAttendanceMapper;

    public List<AttendanceModel> queryAttendance(Date todayDate,Date yesterdayDate,String uri) throws AccessTokenException {

        List<UserValue> userValues = userValueMapper.findByCondition2(new UserValue());
        List<List<UserValue>> divide = this.divide(userValues);
        if (!CollectionUtils.isEmpty(divide)){
            List<AttendanceModel> attendanceModels = new ArrayList<>();
            divide.forEach(e->{
                try {
                    this.handleAttendance(e,attendanceModels,todayDate,yesterdayDate,uri);
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
            });
            log.info("==================crm打卡时间段："+yesterdayDate.getTime()+"---"+todayDate.getTime()+"=======共计"+attendanceModels.size()+"条打卡信息=====");
            return attendanceModels;
        }
        //数据太多请求crm时会报错

        return null;
    }

    private List<List<UserValue>> divide(List<UserValue> userValues){
        List<List<UserValue>> divideList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userValues)){
            int size = userValues.size();
            int i1 = size / 100;
            int i2 = size % 100;
            if (size <= 100){//  <=100
                divideList.add(userValues);
            }else if (i2 != 0){                         //   >100 且不为100整数倍
                for (int i = 0; i < i1; i++) {
                    List<UserValue> userValues1 = userValues.subList(i * 100, i * 100+99);
                    divideList.add(userValues1);
                }
                List<UserValue> userValues1 = userValues.subList(i1 * 100, size);
                divideList.add(userValues1);
            }else{                                      //>100 且为100整数倍
                for (int i = 0; i < i1; i++) {
                    List<UserValue> userValues1 = userValues.subList(i * 100, i * 100+99);
                    divideList.add(userValues1);
                }
            }
        }
        return divideList;
    }

    private void handleAttendance(List<UserValue> userValues,List<AttendanceModel> attendanceModels,Date todayDate,Date yesterdayDate,String uri) throws AccessTokenException {
        if (!CollectionUtils.isEmpty(userValues)){
            List<String> collect = userValues.stream().map(UserValue::getOpenuserid).collect(Collectors.toList());
            AttendanceQueryArg arg = new AttendanceQueryArg( todayDate.getTime(),yesterdayDate.getTime(), collect);
            int i = 0;
            int size;
            do {
                i++;
                arg.setPageNumber(i);
                AttendanceQueryResult queryResult = addressBookManager.queryAttendance(arg,uri);
                JSONArray datas = queryResult.getDatas();
                List<AttendanceModel> attendanceModels1 = JSON.parseArray(datas.toJSONString(), AttendanceModel.class);
                attendanceModels.addAll(attendanceModels1);
                size = datas.size();
            }while (size != 0);
        }
    }


    public void addAttendanceMysql(Date todayDate,Date yesterdayDate,String uri) throws AccessTokenException {

        List<AttendanceModel> list = this.queryAttendance(todayDate,yesterdayDate,uri);
        if (!CollectionUtils.isEmpty(list)){
            list.forEach(e->{
                CrmAttendance crmAttendance = e.valueOf();
                try {
                    crmAttendanceMapper.insert(crmAttendance);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            });
        }
    }

    /**
     * 一天多次打卡，只记录第一次后最后一次打卡
     * 此方法仅用在打卡记录为同一天的
     * @param attendanceModels
     * @return
     */
    private List<AttendanceModel> passMidAttendance(List<AttendanceModel> attendanceModels,int item){
        List<AttendanceModel> listAll = new ArrayList<>();
        Map<String, List<AttendanceModel>> collect = attendanceModels.stream().collect(Collectors.groupingBy(AttendanceModel::getUserName));
        collect.forEach((k,v)->{
            if (v != null && v.size()>2){
                if (item == 2) {
                    AttendanceModel attendanceModelMax = v.stream().max(Comparator.comparing(AttendanceModel::getCheckinsTimeStamp)).get();
                    AttendanceModel attendanceModelMin = v.stream().min(Comparator.comparing(AttendanceModel::getCheckinsTimeStamp)).get();
                    listAll.add(attendanceModelMax);
                    listAll.add(attendanceModelMin);
                }
                if (item == 1){
                    AttendanceModel attendanceModelMax = v.stream().max(Comparator.comparing(AttendanceModel::getCheckTime)).get();
                    AttendanceModel attendanceModelMin = v.stream().min(Comparator.comparing(AttendanceModel::getCheckTime)).get();
                    listAll.add(attendanceModelMax);
                    listAll.add(attendanceModelMin);
                }
            }else {
                listAll.addAll(v);
            }
        });
        return listAll;
    }

    public List<AttendanceModel> query(String uri) throws AccessTokenException {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        ZonedDateTime zonedDateTime = today.atStartOfDay(ZoneId.systemDefault());
        Date todayDate = Date.from(zonedDateTime.toInstant());
        ZonedDateTime yzonedDateTime = yesterday.atStartOfDay(ZoneId.systemDefault());
        Date yesterdayDate = Date.from(yzonedDateTime.toInstant());
        System.out.println(todayDate+"---"+yesterdayDate);
        return queryAttendance(todayDate,yesterdayDate,uri);
    }

    @Scheduled(cron = "0 20 0 1/1 * ?")
    public void addAttendanceMysql() throws AccessTokenException {
        String uri = "/cgi/attendance/find";
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        ZonedDateTime zonedDateTime = today.atStartOfDay(ZoneId.systemDefault());
        Date todayDate = Date.from(zonedDateTime.toInstant());
        ZonedDateTime yzonedDateTime = yesterday.atStartOfDay(ZoneId.systemDefault());
        Date yesterdayDate = Date.from(yzonedDateTime.toInstant());
        List<AttendanceModel> list = this.queryAttendance(todayDate,yesterdayDate,uri);
        List<AttendanceModel> attendanceModels = passMidAttendance(list,1);
        if (!CollectionUtils.isEmpty(attendanceModels)){
            attendanceModels.forEach(e->{
                CrmAttendance crmAttendance = e.valueOf();
                try {
                    crmAttendanceMapper.insert(crmAttendance);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            });
        }
    }

    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void addOutAttendanceMysql() throws AccessTokenException {
        String uri = "/cgi/outsideAttendance/find";
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        ZonedDateTime zonedDateTime = today.atStartOfDay(ZoneId.systemDefault());
        Date todayDate = Date.from(zonedDateTime.toInstant());
        ZonedDateTime yzonedDateTime = yesterday.atStartOfDay(ZoneId.systemDefault());
        Date yesterdayDate = Date.from(yzonedDateTime.toInstant());
        List<AttendanceModel> list = this.queryAttendance(todayDate,yesterdayDate,uri);
        List<AttendanceModel> attendanceModels = passMidAttendance(list,2);
        if (!CollectionUtils.isEmpty(attendanceModels)){
            attendanceModels.forEach(e->{
                CrmAttendance crmAttendance = e.valueOf();
                try {
                    crmAttendanceMapper.insert(crmAttendance);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            });
        }
    }
}
