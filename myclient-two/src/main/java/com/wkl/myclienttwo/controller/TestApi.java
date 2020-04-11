package com.wkl.myclienttwo.controller;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface TestApi {

    public String getTest(@RequestParam Map<String,Object> param);
}
