package com.wkl.myclienttwo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController implements TestApi{

    @Override
    @GetMapping("getTest")
    public String getTest(@RequestParam Map<String,Object> param){
        System.out.println("调用到这里了....."+new Date().getTime());
        return "我是two,你的参数id是"+param.get("id");
    }

    @PostMapping("postTest")
    public String postTest(@RequestBody Map<String,Object> param){
        System.out.println("调用到这里了....."+new Date().getTime());
        return "我是two,你的参数id是"+param.get("id");
    }
}
