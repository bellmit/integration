package com.qunjie;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.jindie.saleorder.save.model.SaleOrderEntity;
import kingdee.bos.webapi.client.ApiClient;
import kingdee.bos.webapi.client.K3CloudApiClient;

import java.util.List;


public class DemoTest {
//    static String K3CloudURL = "http://47.100.203.167/K3Cloud/";
//    static String dbId = "5f46250a7ad252";
//    static String uid = "administrator";
//    static String pwd = "999999999";
//    static int lang = 2052;


//    static String K3CloudURL = "http://localhost/K3Cloud/";
//    static String dbId = "5fcde6a509690a";
//    static String uid = "Administrator";
//    static String pwd = "888888";
//    static int lang = 2052;

    //    static String K3CloudURL = "http://localhost/K3Cloud/";
//    static String dbId = "5fcde6a509690a";
//    static String pwd = "888888";
    static String K3CloudURL = "http://47.100.203.167/K3Cloud/";
    static String dbId = "5f46250a7ad252";
    static String pwd = "999999999";
    static String uid = "Administrator";
    static int lang = 2052;


    public static void main(String[] args) throws Exception {
//saveCurrency();
//query();
//batchsaveCurrency();
//        testall();

//        UnAudit();
//        delete();
    }
    public static void testall() throws Exception{
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sContent = "{\"FormId\":\"PUR_PurchaseOrder\","+// 采购订单formid
                    "\"TopRowCount\":2,"+// 最多允许查询的数量，0或者不要此属性表示不限制
                    "\"Limit\":3,"+// 分页取数每页允许获取的数据，最大不能超过2000
                    "\"StartRow\":0,"+// 分页取数开始行索引，从0开始，例如每页10行数据，第2页开始是10，第3页开始是20
//"\"FilterString\":\"FMaterialId.FNumber like 'HG_TEST%'\","+// 过滤条件
//"\"FilterString\":\"FBillNo='CGDD000008'\","+// 过滤条件
                    "\"OrderString\":\"FID ASC\","+// 排序条件
                    "\"FieldKeys\":\"FID,FSupplierId,FMaterialId.FNumber,FMaterialName\"}";// 获取采购订单数据参数，内码，供应商id，物料id,物料编码，物料名称

            List<List<Object>> sResult = client.executeBillQuery(sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);

            System.out.println("CurrencyTest success:"+sResult);
            String sFormId = "BD_Currency";
            String content = "{\"Numbers\":[\"PRENB00016\"]}";
            String result1 = client.delete(sFormId, content);

            System.out.println("CurrencyTest success:"+result1);


        }
    }

    public static void saveCurrency() throws Exception{

        ApiClient client = new ApiClient(K3CloudURL);

        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "SAL_SaleOrder";
            String sContent = "{\"IsDeleteEntry\":true,\"Model\":{\"FBillNo\":\"bzht00009\",\"FBillTypeID\":{\"FNUMBER\":\"XSDD01_SYS\"},\"FCustId\":{\"FNumber\":\"CUST2168\"},\"FDate\":\"2020-12-14\",\"FSalOrderTrace\":[],\"FSaleOrderClause\":[],\"FSaleOrderEntry\":[{\"FDeliveryDate\":\"2020-12-14\",\"FMaterialId\":{\"FNumber\":\"L.02.22\"},\"FOrderEntryPlan\":[{\"FPlanQty\":23}],\"FQty\":23,\"FSettleOrgIds\":{\"FNumber\":\"100\"},\"FTaxPrice\":3222.0},{\"FDeliveryDate\":\"2020-12-14\",\"FIsFree\":\"1\",\"FMaterialId\":{\"FNumber\":\"L.01.08\"},\"FOrderEntryPlan\":[{\"FPlanQty\":12}],\"FQty\":12,\"FSettleOrgIds\":{\"FNumber\":\"100\"},\"FTaxPrice\":0.0},{\"FDeliveryDate\":\"2020-12-14\",\"FMaterialId\":{\"FNumber\":\"L.02.09\"},\"FOrderEntryPlan\":[{\"FPlanQty\":233}],\"FQty\":233,\"FSettleOrgIds\":{\"FNumber\":\"100\"},\"FTaxPrice\":3000.0}],\"FSaleOrderFinance\":{\"FSettleCurrId\":{\"FNumber\":\"PRE001\"}},\"FSaleOrderPlan\":[{\"FNeedRecAdvance\":\"true\"}],\"FSaleOrgId\":{\"FNumber\":\"100\"},\"FSalerId\":{\"FNumber\":\"YG0059\"}}}\n";
            SaleOrderEntity saleOrderEntity = JSONObject.parseObject(sContent, SaleOrderEntity.class);
            String s = new Gson().toJson(saleOrderEntity);
            String sResult = client.execute("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,s},String.class);

            System.out.println("CurrencyTest success:"+sResult);


        }
    }

    public static void batchsaveCurrency() throws Exception{
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "BD_Currency";
            String sContent = "{\"Creator\":\"\",\"NeedUpDateFields\":[\"\"],\"Model\":[{\"FNumber\":\"PRENB000216\",\"FName\":\"你是噢吗26　？\",\"FCODE\":\"CNYY\",\"FPRICEDIGITS\":6,\"FAMOUNTDIGITS\":2,\"FPRIORITY\":0,\"FCURRENCYSYMBOLID\":{\"FNumber\":\"CS002\"},\"FIsShowCSymbol\":true},"+
                    "{\"FNumber\":\"PRENB00036\",\"FName\":\"你是噢吗16　？\",\"FCODE\":\"CNYY\",\"FPRICEDIGITS\":6,\"FAMOUNTDIGITS\":2,\"FPRIORITY\":0,\"FCURRENCYSYMBOLID\":{\"FNumber\":\"CS002\"},\"FIsShowCSymbol\":true}]}";

            String sResult = client.batchSave(sFormId, sContent);

            System.out.println("CurrencyTest success:"+sResult);


        }
    }
    public static void query() throws Exception{
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sContent = "{\"FormId\":\"PUR_PurchaseOrder\","+// 采购订单formid
                    "\"TopRowCount\":100,"+// 最多允许查询的数量，0或者不要此属性表示不限制
                    "\"Limit\":100,"+// 分页取数每页允许获取的数据，最大不能超过2000
                    "\"StartRow\":0,"+// 分页取数开始行索引，从0开始，例如每页10行数据，第2页开始是10，第3页开始是20
//"\"FilterString\":\"FMaterialId.FNumber like 'HG_TEST%'\","+// 过滤条件
//"\"FilterString\":\"FBillNo='CGDD000008'\","+// 过滤条件
                    "\"OrderString\":\"FID ASC\","+// 排序条件
                    "\"FieldKeys\":\"FID,FSupplierId,FMaterialId.FNumber,FMaterialName\"}";// 获取采购订单数据参数，内码，供应商id，物料id,物料编码，物料名称

            List<List<Object>> sResult = client.executeBillQuery(sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);

            System.out.println("CurrencyTest success:"+sResult);
        }
    }

    public static void delete() throws Exception {
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "SAL_SaleOrder";
            String sContent = "{\"Numbers\":[\"222222323\"]}";
            String delete = client.delete(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
        }
    }

    public static void UnAudit() throws Exception {
        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "SAL_SaleOrder";
            String sContent = "{\"Numbers\":[\"222222323\"]}";
            String delete = client.unAudit(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
        }
    }
}