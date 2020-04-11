package com.rei.javaDemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rei.javaDemo.service.ZsxqService;
import com.rei.javaDemo.utils.HttpUtil;
import org.springframework.stereotype.Service;

@Service
public class ZsxqServiceimpl implements ZsxqService {
    private String url = "";
    private String cookie = "";

    /**
     * 入口
     */
    @Override
    public void start(){
        String responseStr =  HttpUtil.sendGet("https://api.zsxq.com/v1.10/groups/2421112121/topics?scope=all&count=20&end_time=2020-01-16T09%3A33%3A48.323%2B0800",null);

        JSONObject response = JSONObject.parseObject(responseStr);
        if (!response.getBoolean("succeeded")){
            return;
        }
        JSONObject respData = response.getJSONObject("resp_data");
        JSONArray list = respData.getJSONArray("topics");
        int size = list.size();
        for (int i = 0; i < size; i++) {

        }
        int a = 0;
    }
}
