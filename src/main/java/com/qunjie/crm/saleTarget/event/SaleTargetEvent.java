package com.qunjie.crm.saleTarget.event;

import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.event.SaleTargetGREvent
 *
 * @author whs
 * Date:   2021/3/19  13:41
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class SaleTargetEvent extends ApplicationEvent {

    private SaleTargetModel saleTargetModel;

    private String year;

    private String month;

    private String sname;

    private String team;

    private String area;

    public SaleTargetEvent(Object source, SaleTargetModel saleTargetModel, String year, String month, String sname, String team, String area) {
        super(source);
        this.saleTargetModel = saleTargetModel;
        this.year = year;
        this.month = month;
        this.sname = sname;
        this.team = team;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public SaleTargetModel getSaleTargetModel() {
        return saleTargetModel;
    }

    public void setSaleTargetModel(SaleTargetModel saleTargetModel) {
        this.saleTargetModel = saleTargetModel;
    }

    public SaleTargetEvent(Object source) {
        super(source);
    }

    public SaleTargetEvent(Object source, SaleTargetModel saleTargetModel, String year, String month, String sname) {
        super(source);
        this.saleTargetModel = saleTargetModel;
        this.year = year;
        this.month = month;
        this.sname = sname;
    }
}
