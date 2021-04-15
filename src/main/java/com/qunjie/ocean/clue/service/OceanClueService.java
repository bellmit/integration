package com.qunjie.ocean.clue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunjie.crm.leadsObj.service.LeadsObjService;
import com.qunjie.ocean.clue.model.OceanClueModel;
import com.qunjie.ocean.servcie.OCeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.clue.service.OceanClueService
 *
 * @author whs
 * Date:   2021/3/5  13:42
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class OceanClueService {

    @Autowired
    OCeanService oCeanService;

    @Autowired
    LeadsObjService leadsObjService;

    public void syncClue(String start,String end) {
        int page = 1;
        List<OceanClueModel> list = new ArrayList<>();
        JSONObject clueList = oCeanService.getClueList(start, end, page);
        if (clueList != null) {
            int totalPage = clueList.getJSONObject("data").getJSONObject("page_info").getInteger("total_page");
            addAllClue(list,clueList);
            if (totalPage > 1){
                for (int i = 2; i <= totalPage; i++) {
                    JSONObject clueList1 = oCeanService.getClueList(start, end, i);
                    addAllClue(list,clueList1);
                }
            }
        }
        log.info("时间："+start+"---"+ end +",线索条数为==================="+list.size());
//        //测试一条
//        leadsObjService.leadsObjAdd(Arrays.asList(list.get(0)));
        leadsObjService.leadsObjAdd(list);
        log.info("销售线索插入结束=====================");

    }

    public void syncClue(String start,String end,List<String> ids) {
        int page = 1;
        List<OceanClueModel> list = new ArrayList<>();
        JSONObject clueList = oCeanService.getClueList(start, end, page);
        if (clueList != null) {
            int totalPage = clueList.getJSONObject("data").getJSONObject("page_info").getInteger("total_page");
            addAllClue(list,clueList);
            if (totalPage > 1){
                for (int i = 2; i <= totalPage; i++) {
                    JSONObject clueList1 = oCeanService.getClueList(start, end, i);
                    addAllClue(list,clueList1);
                }
            }
        }
        List<OceanClueModel> collect = list.stream().filter(e -> ids.contains(e.getClue_id())).collect(Collectors.toList());
        log.info("时间："+start+"---"+ end +",线索条数为==================="+collect.size());
        leadsObjService.leadsObjAdd(collect);
        log.info("销售线索插入结束=====================");
    }

    private void addAllClue(List<OceanClueModel> list,JSONObject clueList){
        if (clueList != null) {
            JSONArray jsonArray = clueList.getJSONObject("data").getJSONArray("list");
            List<OceanClueModel> oceanClueModels = jsonArray.toJavaList(OceanClueModel.class);
            list.addAll(oceanClueModels);
        }
    }

    @Scheduled(cron = "0 30 0 1/1 * ?")
    public void scheduledClue(){
        LocalDate today = LocalDate.now();
        LocalDate yestoday = today.minusDays(1);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = yestoday.format(fmt);
        this.syncClue(dateStr,dateStr);
    }
}
