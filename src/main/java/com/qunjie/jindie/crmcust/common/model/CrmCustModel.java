package com.qunjie.jindie.crmcust.common.model;/**
 * Created by whs on 2020/12/25.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.marketingEvent.model.MarketingEventModel;
import com.qunjie.crm.marketingEvent.service.MarketingEventService;
import com.qunjie.jindie.crmcust.common.CrmCust;
import com.qunjie.jindie.crmcust.common.CrmCustHB;
import com.qunjie.jindie.saleorder.save.constants.DefaultValue;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.crmcust.common.model.CrmCustModel
 *
 * @author whs
 *         Date:   2020/12/25  16:19
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class CrmCustModel {

    @Describe("主键")
    private Integer FCUSTID;

    @Describe(value = "结算币别",describe = "必填")
    private Entity2 FTRADINGCURRID;

    @Describe(value = "创建组织",describe = "必填")
    private Entity2 FCreateOrgId;

    @Describe(value = "客户名称",describe = "必填")
    private String FName;

    @Describe(value = "纳税登记号",describe = "必填")
    private String FTAXREGISTERCODE;

    @Describe(value = "联系电话",describe = "必填")
    private String FTEL;

    @Describe(value = "客户编码")
    private String FNumber;

    @Describe("备注")
    private String FDescription;

    @Describe(value = "开户银行",describe = "必填")
    private String FOPENBANKNAME;

    @Describe(value = "银行账号",describe = "必填")
    private String FBANKCODE;

    @Describe(value = "客户类别",describe = "必填")
    private Entity2 FCustTypeId;

    @Describe("来源")
    private String F_TOM_TEXT1;

    @Describe("市场活动名称")
    private String F_TOM_TEXT2;

    @Describe(value = "通讯地址",describe = "必填")
    private String FADDRESS;

    public static CrmCustModel valuesOf(CrmCust crmCust) throws AccessTokenException {
        CrmCustModel crmCustModel = new CrmCustModel();
        crmCustModel.setFName(crmCust.getName());
        crmCustModel.setFNumber(crmCust.getAccount_no());
        crmCustModel.setFTRADINGCURRID(new Entity2(DefaultValue.FSETTLECURRID));
        crmCustModel.setFCreateOrgId(new Entity2(DefaultValue.FSALEORGID));
        crmCustModel.setFTAXREGISTERCODE(DefaultValue.FTAXREGISTERCODE);
        crmCustModel.setFTEL(StringUtils.isNotEmpty(crmCust.getTel()) ? crmCust.getTel() : "1");
        crmCustModel.setFCustTypeId(new Entity2(DefaultValue.FCUSTTYPEID));
        crmCustModel.setFADDRESS(StringUtils.isNotEmpty(crmCust.getAddress()) ? crmCust.getAddress(): "地址暂未录入");
        crmCustModel.setFDescription(crmCust.getRemark());
        if (!StringUtils.isBlank(crmCust.getAccount_source())){
            HashMap<String, String> map = new HashMap<String, String>() {
                {
                    put("1", "400电话");
                    put("2", "市场活动");
                    put("3", "转介绍");
                    put("4", "自主开发");
                    put("1HgaZlFTv", "网站");
                    put("5", "伙伴报备");
                    put("other", "其他");
                }
            };
            crmCustModel.setF_TOM_TEXT1(map.get(crmCust.getAccount_source()));;
        }
        if (!StringUtils.isBlank(crmCust.getField_ip1yc__c())){
            MarketingEventService marketingEventService = SpringBeanUtils.getBean(MarketingEventService.class);
            List<MarketingEventModel> query = marketingEventService.query(crmCust.getField_ip1yc__c());
            crmCustModel.setF_TOM_TEXT2(CollectionUtils.isEmpty(query) ? "" : query.get(0).getName());
        }
        return crmCustModel;
    }

    public static CrmCustModel valuesOf(CrmCustHB crmCust) throws AccessTokenException {
        CrmCustModel crmCustModel = new CrmCustModel();
        crmCustModel.setFName(crmCust.getName());
        crmCustModel.setFTRADINGCURRID(new Entity2(DefaultValue.FSETTLECURRID));
        crmCustModel.setFCreateOrgId(new Entity2(DefaultValue.FSALEORGID));
        crmCustModel.setFTAXREGISTERCODE(DefaultValue.FTAXREGISTERCODE);
        crmCustModel.setFTEL(StringUtils.isEmpty(crmCust.getTel()) ? "1" : crmCust.getTel());
        crmCustModel.setFCustTypeId(new Entity2(DefaultValue.FCUSTTYPEID2));
        crmCustModel.setFADDRESS(StringUtils.isNotEmpty(crmCust.getAddress()) ? crmCust.getAddress(): "地址暂未录入");
        crmCustModel.setFDescription(crmCust.getRemark());
        if (!StringUtils.isBlank(crmCust.getField_u66jB__c())){
            HashMap<String, String> map = new HashMap<String, String>() {
                {
                    put("Jlybj1C2c", "400电话");
                    put("UyegsLBe0", "市场活动");
                    put("0X1tVdcQa", "转介绍");
                    put("b4K3TDoer", "自主开发");
                    put("option1", "网站");
                    put("o5ME86DPm", "伙伴报备");
                    put("other", "其他");
                }
            };
            crmCustModel.setF_TOM_TEXT1(map.get(crmCust.getField_u66jB__c()));;
        }
        return crmCustModel;
    }

    public String[] getField(){
        Class aClass =this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        List<String> list = Arrays.stream(declaredFields).filter(e->!e.getName().equals("FNumber")).map(Field::getName).collect(Collectors.toList());
        String[] strings = list.toArray(new String[list.size()]);
        return strings;
    }
}
