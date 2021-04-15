package com.qunjie.axis.service;

import org.springframework.stereotype.Service;

@Service
public interface OAService {

    //保存销售订单
    String SaveSaleOrder(String requestid) throws Exception;

    //删除销售订单
    String DeleteSaleOrder(String requestid) throws Exception;

    //保存合同发票
    int SaveInvoice(String requestid) throws Exception;

    //保存回款
    int SavePayment(String requestid) throws Exception;

    int SaveAchievement(String requestid) throws Exception;

    int SaveConfirmAchievement(String requestid) throws  Exception;
}