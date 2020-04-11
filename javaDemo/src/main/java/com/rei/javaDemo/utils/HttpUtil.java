package com.rei.javaDemo.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * http工具类
 */
public class HttpUtil {

    public static String sendGet(String url, Map<String, String> param) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpGet.addHeader("Accept-Encoding","gzip, deflate, br");
            httpGet.addHeader("Connection","keep-alive");
            httpGet.addHeader("Cookie","UM_distinctid=16fa1b27bfe26e-064fb776253036-34564a7c-1fa400-16fa1b27c012c; abtest_env=product; zsxq_access_token=EBC67CC6-C13C-6BF8-95ED-200F4EB49215");
            httpGet.addHeader("Host","api.zsxq.com");
            httpGet.addHeader("Upgrade-Insecure-Requests","1");
            httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400");
            try {
                if (param != null){
                    for (String key : param.keySet()) {
                        uriBuilder.addParameter(key, param.get(key));
                    }
                }
                HttpResponse response = client.execute(httpGet);
                return EntityUtils.toString(response.getEntity());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                httpGet.releaseConnection();
            }
        }catch (Exception e){
            // 这里主要是uri build错误
            e.printStackTrace();
        }
        return null;
    }

    public static String sendPost(String url, Map<String, String> param) {
        HttpPost httpPost = new HttpPost(url);
        if (param != null) {
            httpPost.setEntity(new StringEntity(JSON.toJSONString(param), Charsets.UTF_8));
        }
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return null;
    }
}
