package com.rei.javaDemo;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.UUID;

@Slf4j
public class TempTest {
    public static void main(String[] args) {
//        log.info("这是个测试...");
//        JSONObject param = new JSONObject();
//        param.set("uuid", UUID.randomUUID());
//        String result = HttpUtil.post("https://dwx-sit.cpic.com.cn/vehicle/new/api/testdemo/user/testApi",param.toString());
//        log.info("请求返回值="+result);

        double bi = NumberUtil.div(2211,3333,2);
        String biStr =  (bi * 100) + "%";
        int a = 0;
    }


}
