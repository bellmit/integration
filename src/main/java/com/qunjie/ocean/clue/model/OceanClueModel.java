package com.qunjie.ocean.clue.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.clue.model.OceanClueModel
 *
 * @author whs
 * Date:   2021/3/5  13:44
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class OceanClueModel {

    private Integer gender;
    private String remark;
    private String advertiser_id;
    private String req_id;
    private String clue_state_name;
    private String city_name;
    private String external_url;
    private String clue_id;
    private List<String> system_tags;
    private Map<String,String> remark_dict;
    private String email;
    private Integer clue_state;
    private String qq;
    private String address;
    private String create_time;
    private String ad_name;
    private String telephone;
    private String clue_owner_name;
    private String clue_source;
    private String create_time_detail;
    private String app_name;
    private String ad_id;
    private String convert_status;
    private String module_id;
    private String site_id;
    private String name;
    private String follow_state_name;
    private Integer clue_type;
    private String location;
    private String advertiser_name;
    private String module_name;
    private Integer age;
}
