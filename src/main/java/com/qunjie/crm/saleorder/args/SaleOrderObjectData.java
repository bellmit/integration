package com.qunjie.crm.saleorder.args;
/**
 * Created by whs on 2021/1/12.
 */

import com.qunjie.crm.partnerObj.model.PartnerObjModel;
import com.qunjie.crm.partnerObj.service.PartnerObjService;
import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.account.model.AccountModel;
import com.qunjie.crm.account.service.AccountService;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName;
import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.service.UserValueService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SaleOrderObjectData
 *
 * @author whs
 *         Date:   2021/1/12  11:03
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderObjectData {

    @Describe("销售订单")
    private String dataObjectApiName = DefaultValues.SALESORDEROBJ;

    @Describe("销售订单编号")
    private String name;

    @Describe("整单折扣")
    private Double discount;

    @Describe("下单日期")
    private String order_time;

    @Describe("销售订单金额")
    private Double order_amount;

    @Describe("交货日期")
    private String delivery_date;

    @Describe("订单类型")
    private String field_laJwf__c;

    @Describe("创建人")
    private List<String> created_by = new ArrayList<>();

    @Describe("负责人")
    private List<String> owner = new ArrayList<>();

    @Describe(value = "客户名称",describe = "必填")
    private String account_id;

    @Describe("oa销售订单编码")
    private String field_Clved__c;

    @Describe("是否子合同")
    private String field_hR8on__c;

    @Describe("是否框架协议")
    private String field_1d9J2__c;

    @Describe("主合同编号")
    private String field_M2uKk__c;

    @Describe("合作伙伴")
    private String partner_id;

    private static final Logger log = LoggerFactory.getLogger(SaleOrderObjectData.class);

    public Map<String,String> valuesOf(List<WorkflowRequestTableField> mains){
        Map<String,String> map = new HashMap<>();
        String currentOpenUserid = DefaultValues.CURRENTOPENUSERID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (CollectionUtils.isEmpty(mains)){
            return map;
        }
        mains.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != FieldName.valuesOf(e.getFieldName())){
                switch (FieldName.valuesOf(e.getFieldName())){
                    case KEHUMC:
                        AccountId(e.getFieldValue());
                        break;
                    case HBKHMC:
                        AccountId(e.getFieldValue());
                        break;
                    case HZHBMC:
                        partnerId(e.getFieldValue());
                        break;
                    case QYR:
                        String crmOpenUserId = getCrmOpenUserId(e.getFieldValue());
                        if (StringUtils.isBlank(crmOpenUserId)){
                            crmOpenUserId = currentOpenUserid;
                        }
                        this.created_by.add(crmOpenUserId) ;
                        this.owner.add(crmOpenUserId) ;
                        map.put(FieldName.QYR.name(),e.getFieldValue());
                        break;
                    case QYRQ:
                        try {
                            this.order_time = String.valueOf(sdf.parse(e.getFieldValue()).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case HTJE:
                        this.order_amount = Double.valueOf(e.getFieldValue());
                        break;
                    case BILLNO:
                        this.field_Clved__c = e.getFieldValue();
                        break;
                    case HTLX:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            /**
                             * 0:新签；1:二次销售 2：Saas新签 3：Saas二次销售  4:试用合同
                             */
                            String[] fanweilx = {"0", "1", "2", "3","4"};
                            String[] crmlx = {"option1", "bH4E0o2qI", "iAthDzVyh", "ld6W8kqk8", "3974lBdp9"};
                            List<String> fwlxList = Arrays.asList(fanweilx);
                            List<String> crmlxList = Arrays.asList(crmlx);
                            if (e.getFieldValue() != null && fwlxList.contains(e.getFieldValue())) {
                                this.field_laJwf__c = crmlxList.get(Integer.valueOf(e.getFieldValue()));
                            }
                            map.put(FieldName.HTLX.name(), e.getFieldValue());
                        }
                        break;
                    case SFZHT:
                        String[] crmSfzht = {"option1","t1SIYx9D4"};
                        String[] fanweiSfzht = {"0","1"};
                        List<String> fanweiSfzhtList = Arrays.asList(fanweiSfzht);
                        List<String> crmSfzhtList = Arrays.asList(crmSfzht);
                        if (e.getFieldValue() != null && fanweiSfzhtList.contains(e.getFieldValue())){
                            this.field_hR8on__c = crmSfzhtList.get(Integer.valueOf(e.getFieldValue()));
                        }
                        break;
                    case ZHTBH:
                        this.field_M2uKk__c = e.getFieldValue();
                        break;
                    case SFKJXY:
                        String[] crmSFKJXY = {"option1","l5R0Q3bOH"};
                        String[] fanweiSFKJXY = {"0","1"};
                        List<String> fanweiSFKJXYList = Arrays.asList(fanweiSFKJXY);
                        List<String> crmSFKJXYList = Arrays.asList(crmSFKJXY);
                        if (e.getFieldValue() != null && fanweiSFKJXYList.contains(e.getFieldValue())){
                            this.field_1d9J2__c = crmSFKJXYList.get(Integer.valueOf(e.getFieldValue()));
                        }
                        break;
                    case XSYBM:
                        if (e.getFieldValue() != null){
                            map.put(FieldName.XSYBM.name(),e.getFieldValue());
                        }
                        break;
                    case XSYSZDQ:
                        if (e.getFieldValue() != null){
                            map.put(FieldName.XSYSZDQ.name(),e.getFieldValue());
                        }
                        break;

                    default:
                        break;
                }
            }
        });
        return map;
    }

    public Map<String,String> QDXSDDvaluesOf(List<WorkflowRequestTableField> mains) {
        Map<String,String> map = new HashMap<>();
        String currentOpenUserid = DefaultValues.CURRENTOPENUSERID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (CollectionUtils.isEmpty(mains)){
            return map;
        }
        mains.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != QDXSDDFieldName.valuesOf(e.getFieldName())){
                switch (QDXSDDFieldName.valuesOf(e.getFieldName())){
                    case KHMC:
                        AccountId(e.getFieldValue());
                        break;
                    case DLSMC:
                        partnerId(e.getFieldValue());
                        break;
                    case XSYXM:
                        String crmOpenUserId = getCrmOpenUserIdByNm(e.getFieldValue());
                        if (StringUtils.isBlank(crmOpenUserId)){
                            crmOpenUserId = currentOpenUserid;
                        }
                        this.created_by.add(crmOpenUserId) ;
                        this.owner.add(crmOpenUserId) ;
                        map.put(QDXSDDFieldName.XSYXM.name(),e.getFieldValue());
                        break;
                    case SQRQ:
                        try {
                            this.order_time = String.valueOf(sdf.parse(e.getFieldValue()).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case DDJE:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.order_amount = Double.valueOf(e.getFieldValue());
                        }
                        break;
                    case DH:
                        this.field_Clved__c = e.getFieldValue();
                        break;
                    case QYLX:
                        String[] fanweilx = {"0","1","2","3"};
                        String[] crmlx = {"option1","bH4E0o2qI","iAthDzVyh","ld6W8kqk8"};
                        List<String> fwlxList = Arrays.asList(fanweilx);
                        List<String> crmlxList = Arrays.asList(crmlx);
                        if (e.getFieldValue() != null && fwlxList.contains(e.getFieldValue())){
                            this.field_laJwf__c = crmlxList.get(Integer.valueOf(e.getFieldValue()));
                        }
                        map.put(QDXSDDFieldName.QYLX.name(),e.getFieldValue());
                        break;
                    case QDJLBM:
                        if (e.getFieldValue() != null){
                            map.put(QDXSDDFieldName.QDJLBM.name(),e.getFieldValue());
                        }
                        break;
                    case QDJLSZDQ:
                        if (e.getFieldValue() != null){
                            map.put(QDXSDDFieldName.QDJLSZDQ.name(),e.getFieldValue());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        return map;
    }

    //通过泛微用户id获取crm用户id
    public static String getCrmOpenUserId(String fanweiId){
        UserValueService userValueService = SpringBeanUtils.getBean(UserValueService.class);
        String res = "";
        UserValue userValue = new UserValue();
        userValue.setUserid(Integer.valueOf(fanweiId));
        List<UserValue> userValues = userValueService.findByCondition2(userValue);
        if (!CollectionUtils.isEmpty(userValues)){
            res = userValues.get(0).getOpenuserid();
        }
        return res;
    }

    //通过泛微姓名获取crm用户id
    public static String getCrmOpenUserIdByNm(String fanweinm){
        UserValueService userValueService = SpringBeanUtils.getBean(UserValueService.class);
        String res = "";
        UserValue userValue = new UserValue();
        userValue.setUsernm(fanweinm);
        List<UserValue> userValues = userValueService.findByCondition2(userValue);
        if (!CollectionUtils.isEmpty(userValues)){
            res = userValues.get(0).getOpenuserid();
        }
        return res;
    }

    /**
     * 通过客户名称查询客户内码
     * @param value
     */
    private void AccountId(String value){
        if (!StringUtils.isBlank(value)) {
            try {
                AccountService accountService = SpringBeanUtils.getBean(AccountService.class);
                List<AccountModel> accountModels = accountService.queryAccountByName(value);
                if (!CollectionUtils.isEmpty(accountModels)) {
                    this.account_id = accountModels.get(0).get_id();
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 通过合作伙伴名称查询合作伙伴内码
     * @param value
     */
    private void partnerId(String value){
        if (!StringUtils.isBlank(value)) {
            try {
                PartnerObjService partnerObjService = SpringBeanUtils.getBean(PartnerObjService.class);
                List<PartnerObjModel> partnerObjModels = partnerObjService.queryPartnerObjByName(value);
                if (!CollectionUtils.isEmpty(partnerObjModels)) {
                    this.partner_id = partnerObjModels.get(0).get_id();
                }
            } catch (AccessTokenException ex) {
                ex.printStackTrace();
            }
        }
    }
}
