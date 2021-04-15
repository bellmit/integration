package com.qunjie.ocean.model;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.model.TokenModel
 *
 * @author whs
 * Date:   2021/3/1  13:43
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class TokenModel {

    private String access_token;
    private Long expires_in;
    private String refresh_token;
    private Long refresh_token_expires_in;

    public TokenModel() {
    }

    public TokenModel(String access_token, Long expires_in, String refresh_token, Long refresh_token_expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.refresh_token_expires_in = refresh_token_expires_in;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("access_token",access_token)
                .add("expires_in",expires_in)
                .add("refresh_token",refresh_token)
                .add("refresh_token_expires_in",refresh_token_expires_in)
                .toString();
    }
}
