package com.qunjie.crm.exception;

@SuppressWarnings("serial")
public class CorpAccessTokenRequestException extends AccessTokenException {
    
    public CorpAccessTokenRequestException(int code, String msg) {
        super(code, msg);
    }
    
}
