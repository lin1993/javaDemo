package com.rei.javaDemo.controller;

import com.rei.javaDemo.example.TransExample;
import com.rei.javaDemo.model.TestParam;
import com.rei.javaDemo.service.TestService;
import com.rei.javaDemo.service.ZsxqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private TestService testService;
    @Autowired
    private ZsxqService zsxqService;
    @Autowired
    private TransExample transExample;

    @RequestMapping("test")
    public String test(@RequestBody TestParam testParam){
        return "123";
    }
    @RequestMapping("test2")
    public String test2(){
        String a = "081";
        int c = a.length();
        if (a.length()>2){
            String subsStr = a.substring(0,1);
            if ("08".equals(subsStr)){
               String d = a.replaceFirst("08","+62");
               int dd = 0;
            }
        }
        testService.dateBaseTest();
        return "123";
    }

    @RequestMapping("startZsxq")
    public String startZsxq(){
        zsxqService.start();
        return "OK";
    }

    /**
     * post情况下入参
     * @return
     */
    @RequestMapping(value = "paramTestPost",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String paramTestPost(@RequestBody TestParam testParam){
        System.out.println(1);
        return "";
    }

    @GetMapping("testTrans")
    public void testTrans(){
        transExample.save();
    }

}
