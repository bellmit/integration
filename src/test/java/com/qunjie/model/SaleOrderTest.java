package com.qunjie.model; /**
 * Created by whs on 2020/12/8.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qunjie.common.response.ApiResult;
import com.qunjie.jindie.saleorder.save.model.SaleOrderEntity;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import com.qunjie.jindie.saleorder.save.vo.*;
import kingdee.bos.webapi.client.K3CloudApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.SaleOrderTest
 *
 * @author whs
 *         Date:   2020/12/8  16:26
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public class SaleOrderTest {

//    static String K3CloudURL = "http://localhost/K3Cloud/";
//    static String dbId = "5fcde6a509690a";
//    static String pwd = "888888";
    static String K3CloudURL = "http://47.100.203.167/K3Cloud/";
    static String dbId = "5f46250a7ad252";
    static String pwd = "999999999";
    static String uid = "Administrator";
    static int lang = 2052;

    public static void main(String[] args) throws Exception {
//        for (int i = 0 ; i< 10;i++){
            test();
//        }
    }

    private static void test() throws Exception {
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "SAL_SaleOrder";
            //财务信息
            FSaleOrderFinance fSaleOrderFinance = getFSaleOrderFinance();

            //订单条款
            List<FSaleOrderClause> fSaleOrderClauses = getFSaleOrderClause();

            //订单明细
            List<FSaleOrderEntry> fSaleOrderEntries = getFSaleOrderEntry();

            //收款计划
            List<FSaleOrderPlan> fSaleOrderPlans = getFSaleOrderPlan();

            //物流跟踪明细
            List<FSalOrderTrace> fSalOrderTraces = getFSalOrderTrace();

            FBillHead model = getModel(fSaleOrderFinance,fSaleOrderClauses,fSaleOrderEntries,fSaleOrderPlans,fSalOrderTraces);
            SaleOrderEntity saleOrderEntity = new SaleOrderEntity(model);

            String s = JSON.toJSONString(saleOrderEntity);

            System.out.println("==================入参========================"+s);

            String sResult = client.execute("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save",
                    new Object[]{sFormId,saleOrderEntity},String.class);

            System.out.println("CurrencyTest success:"+sResult);

            ApiResult apiResult = isSuccess(sResult);
//            if (apiResult.getCode() == 1) {  //保存成功   做提交操作
//                String Id = apiResult.getMessage();
//                String submitEntity = JSON.toJSONString(new SubmitEntity(0L, Id));
//                System.out.println("submitEntity=============" + submitEntity);
//                String submit = client.submit(sFormId, submitEntity);
//                ApiResult success = isSuccess(submit);
//                if (success.getCode() == 1){ //提交成功   做审核操作
//                    String auditEntity = JSON.toJSONString(new AuditEntity(0L, Id));
//                    String audit = client.audit(sFormId, auditEntity);
//                    ApiResult success1 = isSuccess(audit);
//                    if (success1.getCode() == 1){
//                        System.out.println("==============审核成功！！========================");
//                    }
//                }
//
//            }
        }
    }

    private static ApiResult isSuccess(String sResult){
        int code = 0;
        String message = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(sResult);
            if (jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")){
                code = 1;
                message = jsonObject.getJSONObject("Result").getString("Id") != null ?jsonObject.getJSONObject("Result").getString("Id") : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(code,message,null);
    }

    //财务信息
    private static FSaleOrderFinance getFSaleOrderFinance(){
        return new FSaleOrderFinance(new Entity2("PRE001"));
    }

    //订单条款
    private static List<FSaleOrderClause> getFSaleOrderClause(){
        List<FSaleOrderClause> fSaleOrderClauses = new ArrayList<>();
        fSaleOrderClauses.add(new FSaleOrderClause());
        return fSaleOrderClauses;
    }

    //订单明细
    private static List<FSaleOrderEntry> getFSaleOrderEntry(){
        List<FSaleOrderEntry> fSaleOrderEntries = new ArrayList<>();
        FSaleOrderEntry fSaleOrderEntry1 = createFSaleOrderEntry(35);
        FSaleOrderEntry fSaleOrderEntry2 = createFSaleOrderEntry(38);

        fSaleOrderEntries.add(fSaleOrderEntry1);
        fSaleOrderEntries.add(fSaleOrderEntry2);
        return fSaleOrderEntries;
    }

    private static FSaleOrderEntry createFSaleOrderEntry(long num){

        FSaleOrderEntry fSaleOrderEntry = new FSaleOrderEntry();
        fSaleOrderEntry.setFMaterialId(new Entity2("L.02.09"));
//            fSaleOrderEntry.setFUnitID(new Entity2("Pcs"));
        fSaleOrderEntry.setFSettleOrgIds(new Entity2("100"));
        fSaleOrderEntry.setFDeliveryDate("2020-12-03 12:25:45");
        fSaleOrderEntry.setFReserveType("String");
        fSaleOrderEntry.setFOUTLMTUNIT("");
        fSaleOrderEntry.setFTaxPrice(15600.00);
        fSaleOrderEntry.setFQty(num);
//        fSaleOrderEntry.setFIsFree("1");

        List<FTaxDetailSubEntity> fTaxDetailSubEntities = new ArrayList<>();
        fTaxDetailSubEntities.add(new FTaxDetailSubEntity());
        fSaleOrderEntry.setFTaxDetailSubEntity(fTaxDetailSubEntities);

        List<FOrderEntryPlan> fOrderEntryPlans = new ArrayList<>();
        FOrderEntryPlan fOrderEntryPlan = new FOrderEntryPlan();
        fOrderEntryPlan.setFPlanQty(num);
        fOrderEntryPlans.add(fOrderEntryPlan);
        fSaleOrderEntry.setFOrderEntryPlan(fOrderEntryPlans);

        return fSaleOrderEntry;
    }

    //收款计划
    private static List<FSaleOrderPlan> getFSaleOrderPlan(){
        List<FSaleOrderPlan> fSaleOrderPlans = new ArrayList<>();
        fSaleOrderPlans.add(new FSaleOrderPlan());
        return fSaleOrderPlans;
    }

    //物流跟踪明细
    private static List<FSalOrderTrace> getFSalOrderTrace(){
        List<FSalOrderTrace> fSalOrderTraces = new ArrayList<>();
        List<FSalOrderTraceDetail> list = new ArrayList();
        list.add(new FSalOrderTraceDetail());
        fSalOrderTraces.add(new FSalOrderTrace("",list));//物流单号,list
        return fSalOrderTraces;

    }

    //基本信息
    private static FBillHead getModel(FSaleOrderFinance fSaleOrderFinance,List<FSaleOrderClause> fSaleOrderClauses,
                                      List<FSaleOrderEntry> fSaleOrderEntries,List<FSaleOrderPlan> fSaleOrderPlans,List<FSalOrderTrace> fSalOrderTraces){
        FBillHead model = new FBillHead(new Entity2("100"),"2020-12-1",
                new Entity2("CUST2168"),//客户
                new Entity2("YG0059"),//销售员
                new Entity1("XSDD01_SYS"),//销售订单
                fSaleOrderFinance,fSaleOrderClauses,fSaleOrderEntries,fSaleOrderPlans,fSalOrderTraces);
//        model.setFSaleDeptId(new Entity2("BM000001"));
//        model.setFHeadDeliveryWay(new Entity2("JHFS01_SYS"));
//        model.setF_WHS_FileUpdate(BinUtil.fileToBinStr(BinUtil.FILE));
        return model;
    }
}
