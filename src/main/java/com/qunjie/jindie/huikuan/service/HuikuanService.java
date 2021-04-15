package com.qunjie.jindie.huikuan.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.jindie.config.K3CloudConfig;
import com.qunjie.jindie.huikuan.model.HuikuanEntity;
import com.qunjie.jindie.huikuan.vo.FBillHead;
import com.qunjie.jindie.huikuan.vo.FReceiveBillEntry;
import com.qunjie.jindie.util.JindieHelperUtil;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.service.HuikuanService
 *
 * @author whs
 * Date:   2021/1/25  16:31
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class HuikuanService {

    @Autowired
    EmailServiceHelper emailServiceHelper;
    @Autowired
    ApplicationContext applicationContext;

    static final K3CloudConfig k3CloudConfig = SpringBeanUtils.getBean(K3CloudConfig.class);
    static String dbId = k3CloudConfig.getDbId();
    static String uid = k3CloudConfig.getUid();
    static String pwd = k3CloudConfig.getPwd();
    static int lang = 2052;
    static String sFormId = "AR_RECEIVEBILL";

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public int save(WorkflowRequestTable workflowRequestTable) throws Exception {
        log.info("=============================回款推送金蝶==================start!===============");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            if (!CollectionUtils.isEmpty(workflowRequestTable.getDetails())){
                //为减少响应时间，采用多线程处理数据
                List<String> Ids = new CopyOnWriteArrayList<>();
                List<String> list1 = new CopyOnWriteArrayList<>();//因销售订单不存在
                List<String> list2 = new CopyOnWriteArrayList<>();//因其他原因
                AtomicBoolean flag = new AtomicBoolean(true);
                int size = workflowRequestTable.getDetails().size();
                CountDownLatch countDownLatch = new CountDownLatch(size);
                for (List<WorkflowRequestTableField> e : workflowRequestTable.getDetails()){
                    poolExecutor.execute(()->{
                        try {
                            this.save(e,flag,client,Ids,list1,list2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        countDownLatch.countDown();
                    });
                }
                countDownLatch.await();
                /**
                 * 有一条明细保存失败，则删除之前保存成功的数据(此操作可做异步处理，懒得做了...)
                 */
                if (!flag.get()){
                    log.info("==========================回款推送金蝶----失败删除之前保存成功的单据===============================");
                    String message = "时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n"+
                            "因销售订单在金蝶查不到:" + String.join(",",list1)+"\n"+
                            "因其他原因的:" + String.join(",",list2) + "\n";
                    SendEmailEvent sendEmailEvent = new SendEmailEvent(this,message, DefaultEmailAddress.SENDTOAll,"回款推送金蝶失败");
                    applicationContext.publishEvent(sendEmailEvent);
                    if (!CollectionUtils.isEmpty(Ids)){
                        log.info("========================删除的回款Id为=========="+new Gson().toJson(Ids)+"================================================");
                        String join = String.join(",", Ids);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("Ids",join);
                        String sContent = new Gson().toJson(jsonObject);
                        String unAudit = client.unAudit(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
                        String delete = client.delete(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
                        ApiResult success = ApiResult.isSuccess(delete);
                    }
                    return 0;
                }else {
                    log.info("==========================回款推送金蝶----success===============================");
                    return 1;
                }
            }
        }
        return 0;
    }

    private void save(List<WorkflowRequestTableField> e,AtomicBoolean flag,K3CloudApiClient client,List<String> Ids,List<String> list1,List<String> list2) throws Exception {
        FBillHead model = new FBillHead();
        List<FReceiveBillEntry> detail = new ArrayList<>();
        model.valueOf(e);
        FReceiveBillEntry freceiveBillEntry = new FReceiveBillEntry();
        freceiveBillEntry.valueOf(e);
        detail.add(freceiveBillEntry);
        model.setFRECEIVEBILLENTRY(detail);
        /**
         * 金蝶系统中无此关联的销售订单，数据不传
         */
        if (CollectionUtils.isEmpty(freceiveBillEntry.getFASSSALESORDER())){
            log.info("===============================金蝶中不存在此销售订单============="+freceiveBillEntry.getFSALEORDERNO()+"=====================");
            list1.add(freceiveBillEntry.getFSALEORDERNO());
            flag.set(false);
            return;
        }
        HuikuanEntity huikuanEntity = new HuikuanEntity(model);
        String s = new Gson().toJson(huikuanEntity);
        log.debug("==================入参========================"+s);
        String sResult = null;
        try {
            sResult = client.save(sFormId, s);
            ApiResult apiResult = ApiResult.isSuccess(sResult);
            ApiResult apiResult1 = JindieHelperUtil.commitAndAudit(apiResult, client, sFormId);
            if (apiResult != null && apiResult.getCode() == 1){
                Ids.add(apiResult.getMessage());
            }else {
                flag.set(false);
                list2.add(sResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    //单线程回款推送金蝶，因响应时间过长，注掉，采用多线程处理
//    public int save(WorkflowRequestTable workflowRequestTable) throws Exception {
//        log.info("=============================回款推送金蝶==================start!===============");
//        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
//        Boolean result = client.login(dbId, uid, pwd, lang);
//        if(result){
//            if (!CollectionUtils.isEmpty(workflowRequestTable.getDetails())){
//                List<String> Ids = new ArrayList<>();
//                boolean flag = true;
//                for (List<WorkflowRequestTableField> e : workflowRequestTable.getDetails()){
//                    FBillHead model = new FBillHead();
//                    List<FReceiveBillEntry> detail = new ArrayList<>();
//                    model.valueOf(e);
//                    FReceiveBillEntry freceiveBillEntry = new FReceiveBillEntry();
//                    freceiveBillEntry.valueOf(e);
//                    detail.add(freceiveBillEntry);
//                    model.setFRECEIVEBILLENTRY(detail);
//                    /**
//                     * 金蝶系统中无此关联的销售订单，数据不传
//                     */
//                    if (CollectionUtils.isEmpty(freceiveBillEntry.getFASSSALESORDER())){
//                        log.info("===============================金蝶中不存在此销售订单============="+freceiveBillEntry.getFSALEORDERNO()+"=====================");
//                        emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"回款推送金蝶失败","时间:"+
//                                sdf.format(new Date())+"\n金蝶中不存在此销售订单:"+freceiveBillEntry.getFSALEORDERNO());
//                        flag = false;
//                        break;
//                    }
//                    HuikuanEntity huikuanEntity = new HuikuanEntity(model);
//                    String s = new Gson().toJson(huikuanEntity);
//                    log.debug("==================入参========================"+s);
//                    String sResult = null;
//                    try {
//                        sResult = client.save(sFormId, s);
//                        log.debug("Save success results:"+sResult);
//                        ApiResult apiResult = ApiResult.isSuccess(sResult);
//                        ApiResult apiResult1 = JindieHelperUtil.commitAndAudit(apiResult, client, sFormId);
//                        if (apiResult != null && apiResult.getCode() == 1){
//                            Ids.add(apiResult.getMessage());
//                        }else {
//                            flag = false;
//                            emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"回款推送金蝶失败","时间:"+sdf.format(new Date())+"\n金蝶中不存在此销售订单:"+freceiveBillEntry.getFSALEORDERNO());
//                            break;
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//                /**
//                 * 有一条明细保存失败，则删除之前保存成功的数据
//                 */
//                if (!flag){
//                    log.info("==========================回款推送金蝶----失败删除之前保存成功的单据===============================");
//                    if (!CollectionUtils.isEmpty(Ids)){
//                        log.info("========================删除的回款Id为=========="+new Gson().toJson(Ids)+"================================================");
//                        String[] split = Ids.toArray(new String[Ids.size()]);
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("Numbers",split);
//                        String sContent = new Gson().toJson(jsonObject);
//                        String unAudit = client.unAudit(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
//                        String delete = client.delete(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
//                        ApiResult success = ApiResult.isSuccess(delete);
//                    }
//                    return 0;
//                }else {
//                    log.info("==========================回款推送金蝶----success===============================");
//                    return 1;
//                }
//            }
//        }
//        return 0;
//    }
}
