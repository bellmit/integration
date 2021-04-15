package com.qunjie.crm.leadsObj.args;

import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.marketingEvent.model.MarketingEventModel;
import com.qunjie.crm.marketingEvent.service.MarketingEventService;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.ocean.clue.model.OceanClueModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.args.LeadsObjObjectData
 *
 * @author whs
 * Date:   2021/3/5  15:07
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class LeadsObjObjectData {

    private String dataObjectApiName = DefaultValues.LEADSOBJ;
    @Describe("姓名")
    private String name;
    @Describe("线索池")
    private String leads_pool_id = "5fedca9bcf89f6000106ad39";
    @Describe("来源,默认400电话")
    private String source = "1";
    @Describe("下次跟进时间")
    private String next_followed_time;
    @Describe("电话")
    private String tel;
    @Describe("职务")
    private String job_title;
    @Describe("推广渠道")
    private String promotion_channel;
    @Describe("转MQL时间")
    private String changed_to_mql_time;
    @Describe("销售人员退回原因")
    private String back_reason;
    @Describe("线索无效原因")
    private String close_reason;
    @Describe("销售线索详情")
    private String remark;
    @Describe("线索阶段")
    private String leads_stage;
    @Describe("公司")
    private String company;
    @Describe("邮件")
    private String email;
    @Describe("地址")
    private String address;
    @Describe("手机")
    private String mobile;
    @Describe("市场活动名称")
    private String marketing_event_id;
    @Describe("线索类型")
    private String field_m154l__c = "Anw26s5ef";
    @Describe("客户等级")
    private String field_8qela__c = "eg51o57xl";
    @Describe("大区")
    private String field_d023y__c;
    @Describe("转换时间")
    private String transform_time;
    @Describe("负责人")
    private List<String> owner;

    public void valueOf(OceanClueModel model) throws AccessTokenException {
        if (model != null){
            this.company = model.getRemark_dict().computeIfAbsent("您的公司",k->"未填");
            this.name = model.getName();
            this.tel = model.getTelephone().replaceAll("[\\s+]","");
            this.mobile = model.getTelephone().replaceAll("[\\s+]","");
            this.email = model.getEmail();
            MarketingEventService bean = SpringBeanUtils.getBean(MarketingEventService.class);
            List<MarketingEventModel> query = bean.query(null, model.getApp_name());
            this.marketing_event_id = query != null ? query.get(0).get_id() : null;
            this.remark = StringUtils.isBlank(model.getRemark())
                    ?model.getApp_name()+"看到产品广告留的信息，有兴趣进一步了解产品"+"\n地址:"+model.getLocation()
                    :model.getRemark();
            this.address = model.getLocation();
        }

    }
}
