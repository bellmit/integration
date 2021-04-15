package com.qunjie.crm.leadsObj.enums;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.enums.enums
 *
 * @author whs
 * Date:   2021/3/5  16:19
 * Description:     crm中销售线索中所属大区分部及当前负责人;
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum  AreaPersonEnum {
    //华东大区(华东大区给刘艳)
    HD(AreaConstant.HD,"FSUID_AD15246469A1AC4FD63FC15F2ACADD8A"),
    //华南大区(华南  李东长 )
    HN(AreaConstant.HN,"FSUID_C2830B41BE9622CA8E912CF4C2DB078E"),
    //华北大区(华北大区给刘强)
    HB(AreaConstant.HB,"FSUID_3CF407BA9BF1C47334BAE36F11AA0880"),
    //华中大区(华中大区给周米克)
    HZ(AreaConstant.HZ,"FSUID_9F8705CF65016ACC41711A350B1A3EF8"),
    //西南大区(西南大区没人先给倪向华)
    XN(AreaConstant.XN,"FSUID_3B2BAC3E5A3E11548E7061D52D502035"),
    //山东大区(山东大区给张华智)
    SD(AreaConstant.SD,"FSUID_665E2BEE00A3291AC6BB6C1999B6B2FD"),
    //政府线(政府线 朱斌 )
    ZFX(AreaConstant.ZFX,"FSUID_55E6D88C45C4909AC2397DB259D8ABE3"),
    //渠道生态南方大区(南方大区那些城市的 给李阳)
    QDSTS(AreaConstant.QDST,"FSUID_23988C9F8FC621310C3391A22C29D451"),
    //渠道生态北方大区(北方大区那些城市的 给娄涛)
    QDSTN(AreaConstant.QDST,"FSUID_9C01FEF69830F42FE6BAE8E407F6BF9D");

    private String area;
    private String person;
    public String getArea() {
        return area;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setArea(String area) {
        this.area = area;
    }

    AreaPersonEnum(String area, String person) {
        this.area = area;
        this.person = person;
    }

}
