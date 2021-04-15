package com.qunjie.crm.manager;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.beans.results.TextMsgResult;

import java.util.List;

/**
 * 消息管理的接口
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public interface MessageManager {

    /**
     * @param openUserIds 人员id列表
     * @param msgContent 消息内容
     * @return
     * @throws AccessTokenException
     */
    public TextMsgResult sendTextMsg(List<String> openUserIds, String msgContent) throws AccessTokenException;

}
