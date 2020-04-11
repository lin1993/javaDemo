package com.wkl.myclientone.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import com.wkl.myclientone.client.FetchTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test1")
public class TestController {

//    @Resource
    private FetchTwoService fetchTwoService;

    @GetMapping("test")
    public String test(@RequestParam Map<String,Object> param){
        Map<String,Object> params = new HashMap<>();
        params.put("id",param.get("id"));
        String a = fetchTwoService.postTestTwo(params);
        DateTime d = new DateTime();
        return d.toString();
    }

    @GetMapping("testDate")
    public String testDate(){
        Map<String,Object> params = new HashMap<>();
        DateTime d = new DateTime();
        String t = "2020-09-11";
        DateTime tD = DateUtil.parse(t,"yyyy-MM-dd");
        return d.toString();
    }
}
