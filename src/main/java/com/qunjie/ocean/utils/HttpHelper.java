package com.qunjie.ocean.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.utils.HttpHelper
 *
 * @author whs
 * Date:   2021/2/24  10:29
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class HttpHelper {

    public JSONObject doGet(String url,String access_token,Map data) {

        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "GET";
            }
        };

        if (access_token != null) {
            httpEntity.setHeader("Access-Token", access_token);
        }
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            client = HttpClientBuilder.create().build();
            httpEntity.setURI(URI.create(url));
            httpEntity.setEntity(new StringEntity(JSONObject.toJSONString(data), ContentType.APPLICATION_JSON));

            response = client.execute(httpEntity);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return JSONObject.parseObject(result.toString());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public JSONObject doPost(String url,String access_token,Object data) {
        // 构造请求
        HttpPost httpEntity = new HttpPost(url);

        httpEntity.setHeader("Access-Token",access_token);

        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            client = HttpClientBuilder.create().build();
            httpEntity.setEntity(new StringEntity(new Gson().toJson(data), ContentType.APPLICATION_JSON));

            response = client.execute(httpEntity);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return JSONObject.parseObject(result.toString());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public JSONObject doPost(String uri,Map data){

        // 构造请求
        HttpPost httpEntity = new HttpPost(uri);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            client = HttpClientBuilder.create().build();
            httpEntity.setEntity(new StringEntity(JSONObject.toJSONString(data), ContentType.APPLICATION_JSON));

            response = client.execute(httpEntity);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return JSONObject.parseObject(result.toString());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
