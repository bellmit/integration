package com.qunjie.crm.confirmAchievement.service;

import cn.hutool.core.date.DateUtil;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.confirmAchievement.args.ConfirmAchievementAddArg;
import com.qunjie.crm.confirmAchievement.constants.ConfirmAchievementFieldName;
import com.qunjie.crm.confirmAchievement.event.ConfirmAchievementEvent;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.ConfirmAchievementManagerImpl;
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
 * FileName: com.qunjie.crm.confirmAchievement.service.ConfirmAchievementService
 *
 * @author whs
 * Date:   2021/3/10  9:22
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class ConfirmAchievementService {

    @Autowired
    ConfirmAchievementManagerImpl confirmAchievementManager;
    @Autowired
    private ApplicationContext applicationContext;
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public ApiResult ConfirmAchievementAdd(WorkflowRequestTable workflowRequestTable) throws InterruptedException {
        log.info("===============================业绩拆封推送crm=======================start！========================");
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        List<String> list1 = new CopyOnWriteArrayList<>();     //因销售订单crm查不到的问题
        List<String> list3 = new CopyOnWriteArrayList<>();     //因负责人crm查不到的
        List<String> list4 = new CopyOnWriteArrayList<>();     //因其他原因导致的
        AtomicBoolean flag = new AtomicBoolean(true);
        List<String> AddSuccessIds = new CopyOnWriteArrayList<>();     //插入成功的数据id
        List<SaleTargetEvent> saleTargetEvents = new CopyOnWriteArrayList<>();     //销售业绩管理
        CountDownLatch countDownLatch = new CountDownLatch(details.size());
        for (List<WorkflowRequestTableField> detail : details) {
            poolExecutor.execute(()->{
                try {
                    AchievementAdd(detail,list1,list3,list4,flag,AddSuccessIds,saleTargetEvents);
                } catch (AccessTokenException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        if (flag.get()){
            log.info("================销售净业绩插入crm成功！！===========================");
            if (!CollectionUtils.isEmpty(saleTargetEvents)){
                saleTargetEvents.forEach(k->{applicationContext.publishEvent(k);});
            }
            return new ApiResult(1,null,null);
        }else {
            log.info("===========销售净业绩插入crm不成功=========删除已插入的数据================="+String.join(",",AddSuccessIds));
            ConfirmAchievementEvent event = new ConfirmAchievementEvent(this,AddSuccessIds);
            applicationContext.publishEvent(event);
            log.info("====================发送业绩拆封错误邮件信息========================");
            String message = "时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n"+
                    "因销售订单crm查不到的问题:" + String.join(",",list1)+"\n"+
                    "因负责人crm查不到的:" + String.join(",",list3) + "\n"+
                    "因其他原因:" + String.join(",",list4) + "\n";
            SendEmailEvent sendEmailEvent = new SendEmailEvent(this,message, DefaultEmailAddress.SENDTOAll,"销售净业绩数据推送crm失败");
            applicationContext.publishEvent(sendEmailEvent);
            return new ApiResult(0,null,null);
        }
    }

    private void AchievementAdd(List<WorkflowRequestTableField> e,List<String> list1,List<String> list3,List<String> list4,
                                AtomicBoolean flag,List<String> AddSuccessIds,List<SaleTargetEvent> saleTargetEvents) throws AccessTokenException {
        boolean f = true;
        ConfirmAchievementAddArg.ConfirmAchievementObjectData objectData = new ConfirmAchievementAddArg.ConfirmAchievementObjectData();
        Map<String, String> map = objectData.valueOf(e);
        if (StringUtils.isBlank(objectData.getField_K5CmU__c())){
            list1.add(map.get(ConfirmAchievementFieldName.XSHT.name()));
            f = false;
        }
        if (CollectionUtils.isEmpty(objectData.getOwner())){
            if (StringUtils.isBlank(map.get(ConfirmAchievementFieldName.KHJL.name()))){
                list3.add(map.get(ConfirmAchievementFieldName.XSHT.name())+"销售单的销售经理为null!");
                f = false;
            }else {
                list3.add(map.get(ConfirmAchievementFieldName.KHJL.name()));
                f = false;
            }
        }
        if (f){
            ConfirmAchievementAddArg.ConfirmAchievementModel model = new ConfirmAchievementAddArg.ConfirmAchievementModel(objectData);
            CrmAddResult crmAddResult = confirmAchievementManager.saveCustomData(model);
            if (crmAddResult != null && crmAddResult.getErrorCode() != 0){
                flag.set(false);
                list4.add(crmAddResult.getErrorMessage());
            }else {
                //后置事件（更新销售业绩管理）
                Date date = new Date(objectData.getField_dp1Ex__c());
                String year = String.valueOf(DateUtil.year(date));
                String month = String.valueOf(DateUtil.month(date) + 1);
                String sname = String.valueOf(map.get(ConfirmAchievementFieldName.KHJL.name()));
                SaleTargetModel saleTargetModel = new SaleTargetModel();
                saleTargetModel.setField_762N2__c(objectData.getField_eo9K1__c());
                SaleTargetEvent saleTargetEvent = new SaleTargetEvent(this,saleTargetModel,year,month,sname,null,null);
                saleTargetEvents.add(saleTargetEvent);
                AddSuccessIds.add(crmAddResult.getDataId());
            }
        }else {
            flag.set(false);
        }
    }
}
