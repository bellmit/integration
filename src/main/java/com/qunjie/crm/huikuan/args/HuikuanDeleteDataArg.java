package com.qunjie.crm.huikuan.args;

import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

import java.util.List;

@Data
public class HuikuanDeleteDataArg extends BaseArg {

    private String currentOpenUserId;

    private HuikuanDeleteModel data;

    @Data
    public static class HuikuanDeleteModel{
        private List<String> idList;

        private String dataObjectApiName = DefaultValues.PAYMENTOBJ;
    }
}