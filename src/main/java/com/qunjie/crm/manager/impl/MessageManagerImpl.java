package com.qunjie.crm.manager.impl;


import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.args.TextMsgArg;
import com.qunjie.crm.beans.args.TextMsgArg.Text;
import com.qunjie.crm.beans.results.TextMsgResult;
import com.qunjie.crm.manager.MessageManager;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public TextMsgResult sendTextMsg(List<String> openUserIds, String msgContent) throws AccessTokenException {
        TextMsgArg arg = new TextMsgArg();
        arg.setMsgType("text");
        arg.setToUser(openUserIds);
        Text text = arg.new Text();
        text.setContent(msgContent);
        arg.setText(text);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.sendTextMsg(arg);
    }

}
