package com.qunjie.crm.huikuan.service;

import com.google.gson.Gson;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.args.*;
import com.qunjie.crm.huikuan.event.CrmHuikuanEvent;
import com.qunjie.crm.manager.impl.HuikuanManagerImpl;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.service.HuikuanService
 *
 * @author whs
 * Date:   2021/1/27  9:59
 * Description: 回款
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class CrmHuikuanService {

    @Autowired
    EmailServiceHelper emailServiceHelper;
    @Autowired
    HuikuanManagerImpl huikuanManagerImpl;
    @Autowired
    CrmQueryService crmQueryService;
    @Autowired
    ApplicationContext applicationContext;

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    /**
     * 现要一次性把问题数据全统计出来发送消息，并且采用多线程加快处理速度,故此旧方法注掉(此方法功能正常)
     * @param workflowRequestTable
     * @return
     * @throws AccessTokenException
     */
//    public ApiResult huikuanAdd(WorkflowRequestTable workflowRequestTable) throws AccessTokenException {
//        log.info("===============================汇款推送crm=======================start！========================");
//        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
//        /**
//         * 一条泛微回款单包含多条销售订单，如果有失败的，则事务回滚，
//         * 删除前面保存成功的。。全成功算保存crm成功
//         */
//        boolean flag = true;
//        List<String> ids = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(details)){
//            for (List<WorkflowRequestTableField> e : details){
//                HuikuanObjectData huikuanObjectData = new HuikuanObjectData();
//                Map<String, String> ObjectDataMap = huikuanObjectData.valuesOf(e);
//                List<HuikuanDetail> huikuanDetails = new ArrayList<>();
//                HuikuanDetail huikuanDetail = new HuikuanDetail();
//                Map<String, String> map = huikuanDetail.valuesOf(e);
//                if (StringUtils.isBlank(huikuanDetail.getOrder_id())){//销售订单不存在的情况
//                    emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"收款推送crm失败","时间："+
//                            sdf.format(new Date())+"\n泛微中销售订单号为:"+map.get(HuikuanFieldName.HTBHX.name())+"\n在crm中不存在!!");
//                    log.info("crm保存回款失败原因：泛微中销售订单号为"+map.get(HuikuanFieldName.HTBHX.name())+"在crm中不存在!!");
//                    flag = false;
//                    break;
//                }else { //销售订单存在的话，用销售订单的客户作为回款客户，而非oa回款流程中的客户
//                    huikuanObjectData.setAccount_id(map.get("account_id"));
//                }
//                if (StringUtils.isEmpty(huikuanObjectData.getPartner_id())){//合作伙伴不存在的情况(分两种，一个oa那边没有，一个crm系统中没有)
//                    if (ObjectDataMap.get(HuikuanFieldName.HZHB.name()) != null
//                            && ObjectDataMap.get(HuikuanFieldName.HZHB.name()).equals(HuikuanFieldName.HZHB.name())){
//                        //oa数据中没有合作伙伴，不处理，正常推送
//                    }else {
//                        emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll, "收款推送crm失败", "时间：" +
//                                sdf.format(new Date()) + "\n泛微中合作伙伴名称为:" + ObjectDataMap.get(HuikuanFieldName.HZHB.name()) + "\n在crm中不存在!!");
//                        log.info("crm保存回款失败原因：泛微中合作伙伴名称为:" + ObjectDataMap.get(HuikuanFieldName.HZHB.name()) + "在crm中不存在!!");
//                        flag = false;
//                        break;
//                    }
//                }
//                huikuanDetails.add(huikuanDetail);
//                HuikuanProductObj huikuanProductObj = new HuikuanProductObj(huikuanDetails);
//                HuikuanModel huikuanModel = new HuikuanModel(huikuanObjectData,huikuanProductObj);
//                CrmAddResult crmAddResult = huikuanManagerImpl.savePayment(huikuanModel);
//                if (crmAddResult == null || crmAddResult.getErrorCode() != 0){
//                    emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"收款推送crm失败","时间："+
//                            sdf.format(new Date())+"\n原因:"+crmAddResult.toString());
//                    log.info("crm保存回款失败原因："+crmAddResult.toString());
//                    flag = false;
//                    break;
//                }else {
//                    ids.add(crmAddResult.getDataId());
//                }
//            }
//        }
//        //未全部保存成功。删除保存成功的
//        if (!flag){
//            log.info("=============================回款推送crm失败，删除保存成功单据====================================");
//            if (!CollectionUtils.isEmpty(ids)){
//                log.info("=============================，删除保存成功单据集合===================================="+new Gson().toJson(ids));
//                ids.forEach(id->{
//                    try {
//                        BaseResult baseResult = this.huikuanInvalid(id);
//                        if (baseResult != null && baseResult.getErrorCode() == 0){
//                            this.huikuanDelete(id);
//                        }
//                    } catch (AccessTokenException ex) {
//                        ex.printStackTrace();
//                    }
//                });
//            }
//            return new ApiResult(0,null,null);
//        }else {
//            log.info("================================回款推送crm========================success!==========================");
//            return new ApiResult(1,null,ids);
//        }
//    }

    public ApiResult huikuanAdd(WorkflowRequestTable workflowRequestTable) throws AccessTokenException, InterruptedException {
        log.info("===============================回款推送crm=======================start！========================");
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        /**
         * 一条泛微回款单包含多条销售订单，如果有失败的，则事务回滚，
         * 删除前面保存成功的。。全成功算保存crm成功
         */
        if (!CollectionUtils.isEmpty(details)) {
            List<String> ids = new CopyOnWriteArrayList<>();
            List<String> list1 = new CopyOnWriteArrayList<>();//因销售订单号在crm不存在
            List<String> list2 = new CopyOnWriteArrayList<>();//因合作伙伴在crm中不存在
            List<String> list3 = new CopyOnWriteArrayList<>();//其他原因
            AtomicBoolean flag = new AtomicBoolean(true);
            CountDownLatch countDownLatch = new CountDownLatch(details.size());
            for (List<WorkflowRequestTableField> e : details) {
                poolExecutor.execute(() -> {
                    try {
                        this.savePayment(e, list1, list2, list3, flag, ids);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            //未全部保存成功。删除保存成功的
            if (!flag.get()) {
                log.info("=============================回款推送crm失败，删除保存成功单据====================================");
                String message = "时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n"+
                        "因销售订单在crm查不到:" + String.join(",",list1)+"\n"+
                        "因合作伙伴在crm查不到:" + String.join(",",list2)+"\n"+
                        "因其他原因的:" + String.join(",",list3) + "\n";
                SendEmailEvent sendEmailEvent = new SendEmailEvent(this,message,DefaultEmailAddress.SENDTOAll,"回款推送crm失败");
                applicationContext.publishEvent(sendEmailEvent);
                if (!CollectionUtils.isEmpty(ids)) {
                    log.info("=============================删除保存成功单据集合====================================" + new Gson().toJson(ids));
                    CrmHuikuanEvent event = new CrmHuikuanEvent(this,ids);
                    applicationContext.publishEvent(event);
                }
                return new ApiResult(0, null, null);
            } else {
                log.info("================================回款推送crm========================success!==========================");
                return new ApiResult(1, null, ids);
            }
        }
        return new ApiResult(1,null,null);
    }

    private void savePayment(List<WorkflowRequestTableField> e,List<String> list1,List<String> list2,List<String> list3,AtomicBoolean flag,List<String> ids) throws AccessTokenException {
        HuikuanObjectData huikuanObjectData = new HuikuanObjectData();
        Map<String, String> ObjectDataMap = huikuanObjectData.valuesOf(e);
        List<HuikuanDetail> huikuanDetails = new ArrayList<>();
        HuikuanDetail huikuanDetail = new HuikuanDetail();
        Map<String, String> map = huikuanDetail.valuesOf(e);
        if (StringUtils.isBlank(huikuanDetail.getOrder_id())){//销售订单不存在的情况
            list1.add(map.get(HuikuanFieldName.HTBHX.name()));
            log.info("crm保存回款失败原因：泛微中销售订单号为"+map.get(HuikuanFieldName.HTBHX.name())+"在crm中不存在!!");
            flag.set(false);
        }else { //销售订单存在的话，用销售订单的客户作为回款客户，而非oa回款流程中的客户
            huikuanObjectData.setAccount_id(map.get("account_id"));
        }
        if (StringUtils.isEmpty(huikuanObjectData.getPartner_id())){//合作伙伴不存在的情况(分两种，一个oa那边没有，一个crm系统中没有)
            if (ObjectDataMap.get(HuikuanFieldName.HZHB.name()) != null
                    && ObjectDataMap.get(HuikuanFieldName.HZHB.name()).equals(HuikuanFieldName.HZHB.name())){
                //oa数据中没有合作伙伴，不处理，正常推送
            }else {
                list2.add(ObjectDataMap.get(HuikuanFieldName.HZHB.name()));
                log.info("crm保存回款失败原因：泛微中合作伙伴名称为:" + ObjectDataMap.get(HuikuanFieldName.HZHB.name()) + "在crm中不存在!!");
                flag.set(false);
            }
        }
        if (flag.get()) {
            huikuanDetails.add(huikuanDetail);
            HuikuanProductObj huikuanProductObj = new HuikuanProductObj(huikuanDetails);
            HuikuanModel huikuanModel = new HuikuanModel(huikuanObjectData,huikuanProductObj);
            CrmAddResult crmAddResult = huikuanManagerImpl.savePayment(huikuanModel);
            if (crmAddResult == null || crmAddResult.getErrorCode() != 0) {
                list3.add(crmAddResult.toString());
                log.info("crm保存回款失败原因：" + crmAddResult.toString());
                flag.set(false);
            } else {
                ids.add(crmAddResult.getDataId());
            }
        }
    }

    public BaseResult huikuanDelete(String dataId) throws AccessTokenException {
        HuikuanDeleteDataArg.HuikuanDeleteModel data = new HuikuanDeleteDataArg.HuikuanDeleteModel();
        List<String> list = new ArrayList<>();
        list.add(dataId);
        data.setIdList(list);
        return huikuanManagerImpl.deletePayment(data);
    }

    public BaseResult huikuanInvalid(String dataId) throws AccessTokenException{
        HuikuanInvalidArg.HuikuanInvalidData data = new HuikuanInvalidArg.HuikuanInvalidData();
        data.setObject_data_id(dataId);
        return huikuanManagerImpl.invalidPayment(data);
    }
}
