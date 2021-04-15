package com.qunjie.jindie.util;/**
 * Created by whs on 2021/1/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.common.consts.SaveSaleOrderMessage;
import com.qunjie.common.response.ApiResult;
import com.qunjie.jindie.saleorder.audit.model.AuditEntity;
import com.qunjie.jindie.saleorder.submit.model.SubmitEntity;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.util.JindieHelperUtil
 *
 * @author whs
 *         Date:   2021/1/5  10:09
 *         Description:  对金蝶数据保存后，提交+审核；
 *                      对修改的数据，先反审核+修改+提交+审核
 *                      对删除的数据，反审核+删除
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Slf4j
public class JindieHelperUtil {

    //提交+审核
    public static ApiResult commitAndAudit(ApiResult apiResult,K3CloudApiClient client,String sFormId) throws Exception {
        if (apiResult.getCode() == 1) {  //保存成功   做提交操作
            log.debug("================Save success！！======================");
            String Id = apiResult.getMessage();
            String submitEntity = new Gson().toJson(new SubmitEntity(0L, Id));
            log.debug("submitEntity=============" + submitEntity);
            String submit = client.submit(sFormId, submitEntity);
            ApiResult success = ApiResult.isSuccess(submit);
            if (success.getCode() == 1){ //提交成功   做审核操作
                log.debug("=========================Submit success！！============================");
                String auditEntity = new Gson().toJson(new AuditEntity(0L, Id));
                log.debug("auditEntity=============" + auditEntity);
                String audit = client.audit(sFormId, auditEntity);
                ApiResult success1 = ApiResult.isSuccess(audit);
                if (success1.getCode() == 1){
                    log.debug("==============Audit success！！========================");
                    return new ApiResult(1, "SUCCESS",null);
                }
                return new ApiResult(0,SaveSaleOrderMessage.ERROR4,null);
            }
            return new ApiResult(0,SaveSaleOrderMessage.ERROR3,null);
        }
        return new ApiResult(0,SaveSaleOrderMessage.ERROR2,null);
    }

    //反审核
    public static ApiResult UnAudit(String[] numbers,K3CloudApiClient client,String sFormId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Numbers",numbers);
        String sContent = new Gson().toJson(jsonObject);
        String unAudit = client.unAudit(sFormId, sContent);
        ApiResult issuccess = ApiResult.isSuccess(unAudit);
        return issuccess;
    }

    //反审核
    public static ApiResult UnAudit(String id,K3CloudApiClient client,String sFormId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Ids",id);
        String sContent = new Gson().toJson(jsonObject);
        String unAudit = client.unAudit(sFormId, sContent);
        ApiResult issuccess = ApiResult.isSuccess(unAudit);
        return issuccess;
    }

}
