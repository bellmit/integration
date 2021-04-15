package com.qunjie.crm.saleTarget.event;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import com.qunjie.crm.saleTarget.service.SaleTargetService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.event.SaleTargetListener
 *
 * @author whs
 * Date:   2021/3/19  13:45
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class SaleTargetListener implements ApplicationListener<SaleTargetEvent> {

    @Autowired
    SaleTargetService saleTargetService;

    private ReentrantLock lock = new ReentrantLock();

    @SneakyThrows
    @Override
    @Async
    public void onApplicationEvent(SaleTargetEvent saleTargetEvent) {
        lock.lock();
        try {
            SaleTargetModel saleTargetGRModel = saleTargetService.querySaleTargetGR(saleTargetEvent.getYear(), saleTargetEvent.getMonth(), saleTargetEvent.getSname());
            modify(saleTargetEvent, saleTargetGRModel);
            SaleTargetModel saleTargetTEAMModel = saleTargetService.querySaleTargetTeam(saleTargetEvent.getYear(), saleTargetEvent.getMonth(), saleTargetEvent.getTeam());
            modify(saleTargetEvent,saleTargetTEAMModel);
            SaleTargetModel saleTargetAREAModel = saleTargetService.querySaleTargetArea(saleTargetEvent.getYear(), saleTargetEvent.getMonth(), saleTargetEvent.getArea());
            modify(saleTargetEvent,saleTargetAREAModel);
            log.info("=====================销售业绩管理更新结束===============================");
            log.info("=====================detail-gr==============================="+saleTargetGRModel);
            log.info("=====================detail-tm==============================="+saleTargetTEAMModel);
            log.info("=====================detail-area==============================="+saleTargetAREAModel);
        } catch (AccessTokenException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void modify(SaleTargetEvent saleTargetEvent, SaleTargetModel saleTargetModel) {
        SaleTargetModel newsaleTargetModel = new SaleTargetModel();
        SaleTargetModel modifySaleTarget = saleTargetEvent.getSaleTargetModel();
        if (modifySaleTarget != null && saleTargetModel != null) {
            newsaleTargetModel.set_id(saleTargetModel.get_id());
            if (modifySaleTarget.getField_762N2__c() != null && modifySaleTarget.getField_762N2__c() > 0)//实际净业绩
                newsaleTargetModel.setField_762N2__c((saleTargetModel.getField_762N2__c() != null ? saleTargetModel.getField_762N2__c() : 0.0) + modifySaleTarget.getField_762N2__c());
            if (modifySaleTarget.getField_4yaGI__c() != null && modifySaleTarget.getField_4yaGI__c() > 0)//实际回款业绩
                newsaleTargetModel.setField_4yaGI__c((saleTargetModel.getField_4yaGI__c() != null ? saleTargetModel.getField_4yaGI__c() : 0.0) + modifySaleTarget.getField_4yaGI__c());
            if (modifySaleTarget.getField_q4r9M__c() != null && modifySaleTarget.getField_q4r9M__c() != null) {
                double v = saleTargetModel.getField_q4r9M__c() != null ? saleTargetModel.getField_q4r9M__c() : 0.0;
                if (v + modifySaleTarget.getField_q4r9M__c() >= 0) {
                    newsaleTargetModel.setField_q4r9M__c(v + modifySaleTarget.getField_q4r9M__c());
                }
            }
            try {
                saleTargetService.modifySaleTarget(newsaleTargetModel);
            } catch (AccessTokenException e) {
                log.error("==============================销售业绩管理更新失败===========================");
            }
        }
    }
}
