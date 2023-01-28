package com.kkui.common;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ProtocolException;

import java.io.IOException;

/**
 * @Author kkui
 * @Date 2022/8/17
 * @Description 提供http请求
 */
public final class HttpClientUtil {

    public static String LiMaoSendGet(String urlParam,String heads) throws InterruptedException {
        Thread.sleep(1000);

        //禁止重定向
        RequestConfig config = RequestConfig.custom().setRedirectsEnabled(false).build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpGet httpGet = new HttpGet(urlParam);
        httpGet.setHeader("Referer",heads);

        String result = null;
        try{
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getCode() == 302){
                result = response.getHeader("location").getValue();
            }

        } catch (IOException | ProtocolException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
