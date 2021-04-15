package com.qunjie.crm.exception;

@SuppressWarnings("serial")
public class AccessTokenException extends BaseException {

    public AccessTokenException(int code, String msg) {
        super(code, msg);
    }

}
