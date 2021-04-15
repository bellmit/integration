package com.qunjie.crm.achievement.service;

import cn.hutool.core.date.DateUtil;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.achievement.args.AchievementAddArg;
import com.qunjie.crm.achievement.constants.AchievementFieldName;
import com.qunjie.crm.achievement.event.AchievementEvent;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.AchievementManagerImpl;
import com.qunjie.crm.saleTarget.event.SaleTargetEvent;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.service.AchievementCrmService
 *
 * @author whs
 * Date:   2021/2/25  10:10
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class AchievementCrmService {

    @Autowired
    EmailServiceHelper emailServiceHelper;
    @Autowired
    AchievementManagerImpl achievementManager;
    @Autowired
    private ApplicationContext applicationContext;

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    //此方法采用的单线程，运行速度慢(功能无问题)，采用多线程写法，故注释掉
//    public ApiResult AchievementAdd(WorkflowRequestTable workflowRequestTable) throws AccessTokenException {
//        log.info("===============================业绩拆封推送crm=======================start！========================");
//        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
//
//        List<AchievementAddArg.AchievementModel> toBeAdd = new ArrayList<>();//可以插入crm中的数据
//        List<String> list1 = new CopyOnWriteArrayList<>();     //因销售订单crm查不到的问题
//        List<String> list2 = new CopyOnWriteArrayList<>();     //因合作伙伴crm查不到
//        List<String> list3 = new CopyOnWriteArrayList<>();     //因负责人crm查不到的
//        AtomicBoolean flag = new AtomicBoolean(true);
//        List<String> AddIds = new CopyOnWriteArrayList<>();     //插入成功的数据id
//        log.info("=============================校验数据是否合规========================");
//        details.forEach(e->{
//            AchievementAddArg.AchievementObjectData objectData = new AchievementAddArg.AchievementObjectData();
//            Map<String, String> map = objectData.valueOf(e);
//            if (StringUtils.isBlank(objectData.getField_xH1XV__c())){
//                list1.add(map.get(AchievementFieldName.HTBHX.name()));
//            }
//            if (StringUtils.isBlank(objectData.getField_I24W5__c()) && !StringUtils.isBlank(map.get(AchievementFieldName.HZHB.name()))){
//                list2.add(map.get(AchievementFieldName.HZHB.name()));
//            }
//            if (CollectionUtils.isEmpty(objectData.getOwner())){
//                if (StringUtils.isBlank(map.get(AchievementFieldName.KHJL1.name()))){
//                    list3.add(map.get(AchievementFieldName.HTBHX.name())+"销售单的销售经理为null!");
//                }else {
//                    list3.add(map.get(AchievementFieldName.KHJL1.name()));
//                }
//            }
//            if (CollectionUtils.isEmpty(list1) && CollectionUtils.isEmpty(list2) && CollectionUtils.isEmpty(list3)){
//                AchievementAddArg.AchievementModel model = new AchievementAddArg.AchievementModel(objectData);
//                toBeAdd.add(model);
//            }else {
//                flag.set(false);
//            }
//        });
//        //插入crm系统
//        boolean addSuccess = true;
//        List<String> AddSuccessIds = new ArrayList<>();
//        if (flag.get()){
//            log.info("===========================业绩拆分数据合规，做插入crm操作!====================================");
//            for (AchievementAddArg.AchievementModel achievementModel : toBeAdd) {
//                CrmAddResult crmAddResult = achievementManager.saveAchievement(achievementModel);
//                if (crmAddResult.getErrorCode() != 0){
//                    addSuccess = false;
//                    break;
//                }else {
//                    AddSuccessIds.add(crmAddResult.getDataId());
//                }
//            }
//            if (addSuccess){
//                log.info("================业绩拆封插入crm成功！！===========================");
//                return new ApiResult(1,null,null);
//            }else {
//                log.info("===========业绩拆封插入crm不成功=========删除已插入的数据================="+String.join(",",AddSuccessIds));
//                AchievementEvent event = new AchievementEvent(this,AddSuccessIds);
//                applicationContext.publishEvent(event);
//                return new ApiResult(0,null,null);
//            }
//        }else {
//            log.info("==================业绩拆封数据不合规，不做插入crm操作=================");
//            String message = "时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n"+
//                    "因销售订单crm查不到的问题:" + String.join(",",list1)+"\n"+
//                    "因合作伙伴crm查不到:" + String.join(",",list2)+"\n"+
//                    "因负责人crm查不到的:" + String.join(",",list3) + "\n";
//            SendEmailEvent sendEmailEvent = new SendEmailEvent(this,message,DefaultEmailAddress.SENDTOAll,"业绩拆封数据不合规");
//            applicationContext.publishEvent(sendEmailEvent);
//            return new ApiResult(0,null,null);
//        }
//    }

    public ApiResult AchievementAdd(WorkflowRequestTable workflowRequestTable) throws InterruptedException {
        log.info("===============================业绩拆封推送crm=======================start！========================");
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        List<WorkflowRequestTableField> mains = workflowRequestTable.getMains();
        List<String> list1 = new CopyOnWriteArrayList<>();     //因销售订单crm查不到的问题
        List<String> list2 = new CopyOnWriteArrayList<>();     //因合作伙伴crm查不到
        List<String> list3 = new CopyOnWriteArrayList<>();     //因负责人crm查不到的
        List<String> list4 = new CopyOnWriteArrayList<>();     //因其他原因导致的
        AtomicBoolean flag = new AtomicBoolean(true);
        List<String> AddSuccessIds = new CopyOnWriteArrayList<>();     //插入成功的数据id
        List<SaleTargetEvent> saleTargetEvents = new CopyOnWriteArrayList<>();     //销售业绩管理
        CountDownLatch countDownLatch = new CountDownLatch(details.size());
        for (List<WorkflowRequestTableField> detail : details) {
            poolExecutor.execute(()->{
                try {
                    AchievementAdd(detail,list1,list2,list3,list4,flag,AddSuccessIds,mains,saleTargetEvents);
                } catch (AccessTokenException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        if (flag.get()){
            log.info("================业绩拆封插入crm成功！！===========================");
            if (!CollectionUtils.isEmpty(saleTargetEvents)){
                saleTargetEvents.forEach(k->{applicationContext.publishEvent(k);});
            }
            return new ApiResult(1,null,null);
        }else {
            log.info("===========业绩拆封插入crm不成功=========删除已插入的数据================="+String.join(",",AddSuccessIds));
            AchievementEvent event = new AchievementEvent(this,AddSuccessIds);
            applicationContext.publishEvent(event);
            log.info("====================发送业绩拆封错误邮件信息========================");
            String message = "时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n"+
                    "因销售订单crm查不到的问题:" + String.join(",",list1)+"\n"+
                    "因合作伙伴crm查不到:" + String.join(",",list2)+"\n"+
                    "因负责人crm查不到的:" + String.join(",",list3) + "\n"+
                    "因其他原因:" + String.join(",",list4) + "\n";
            SendEmailEvent sendEmailEvent = new SendEmailEvent(this,message,DefaultEmailAddress.SENDTOAll,"业绩拆封数据推送crm失败");
            applicationContext.publishEvent(sendEmailEvent);
            return new ApiResult(0,null,null);
        }
    }

    private void AchievementAdd(List<WorkflowRequestTableField> e,List<String> list1,List<String> list2,List<String> list3,List<String> list4,
                                AtomicBoolean flag,List<String> AddSuccessIds,List<WorkflowRequestTableField> mains,List<SaleTargetEvent> saleTargetEvents) throws AccessTokenException {
        boolean f = true;
        AchievementAddArg.AchievementObjectData objectData = new AchievementAddArg.AchievementObjectData();
        Map<String, String> map = objectData.valueOf(e);
        objectData.valueOfMain(mains);
        if (StringUtils.isBlank(objectData.getField_xH1XV__c())){
            list1.add(map.get(AchievementFieldName.HTBHX.name()));
            f = false;
        }
        if (StringUtils.isBlank(objectData.getField_I24W5__c()) && !StringUtils.isBlank(map.get(AchievementFieldName.HZHB.name()))){
            list2.add(map.get(AchievementFieldName.HZHB.name()));
            f = false;
        }
        if (CollectionUtils.isEmpty(objectData.getOwner())){
            if (StringUtils.isBlank(map.get(AchievementFieldName.KHJL1.name()))){
                list3.add(map.get(AchievementFieldName.HTBHX.name())+"销售单的销售经理为null!");
                f = false;
            }else {
                list3.add(map.get(AchievementFieldName.KHJL1.name()));
                f = false;
            }
        }
        if (f){
            AchievementAddArg.AchievementModel model = new AchievementAddArg.AchievementModel(objectData);
            CrmAddResult crmAddResult = achievementManager.saveAchievement(model);
            if (crmAddResult != null && crmAddResult.getErrorCode() != 0){
                flag.set(false);
                list4.add(crmAddResult.getErrorMessage());
            }else {
                //后置事件（更新销售业绩管理）
                Date date = new Date(objectData.getField_2a6kX__c());
                String year = String.valueOf(DateUtil.year(date));
                String month = String.valueOf(DateUtil.month(date) + 1);
                String sname = String.valueOf(map.get(AchievementFieldName.KHJL1.name()));
                String team = String.valueOf(map.get(AchievementFieldName.SZTD.name()));
                String area = String.valueOf(map.get(AchievementFieldName.SZDQ.name()));
                SaleTargetModel saleTargetModel = new SaleTargetModel();
                saleTargetModel.setField_4yaGI__c(objectData.getField_8GE9P__c());
                SaleTargetEvent saleTargetEvent = new SaleTargetEvent(this,saleTargetModel,year,month,sname,team,area);
                saleTargetEvents.add(saleTargetEvent);
                AddSuccessIds.add(crmAddResult.getDataId());
            }
        }else {
            flag.set(false);
        }
    }
}
