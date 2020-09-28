package com.rei.javaDemo.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.rei.javaDemo.aspect.MyAspect;
import com.rei.javaDemo.example.ThreadExample;
import com.rei.javaDemo.example.TransExample;
import com.rei.javaDemo.model.TestParam;
import com.rei.javaDemo.service.TestService;
import com.rei.javaDemo.service.ZsxqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private TestService testService;
    @Autowired
    private ZsxqService zsxqService;
    @Autowired
    private TransExample transExample;
    @Autowired
    private ThreadExample threadExample;

    @RequestMapping("test")
    public String test(@RequestBody TestParam testParam, HttpServletRequest request){
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
    @PostMapping("encryption")
    public String encryption(@RequestBody TestParam testParam){
        System.out.println(1);
        return JSON.toJSONString(testParam);
    }

    @GetMapping("testTrans")
    public void testTrans(){
        transExample.save();
    }

    // @MyAspect("hhh")
    @GetMapping("testAspect")
    public void testAspect(@RequestParam("Str")String str ){
        String asStr = testService.aspectTest();
        System.out.println("测试自定义注解");
    }

    /**
     * 多线程极端测试
     */
    @GetMapping("testMultiThread")
    public void testMultiThread(){
        threadExample.MultiThread();
    }


    @GetMapping("testHttp")
    public String testHttp(){
        Runnable runnable = () -> asyncPost();
        ThreadUtil.execAsync(runnable);
        return "handler";
    }

    void asyncPost(){
        int num = 0;
        int succ = 0;
        int fail = 0;
        while (true){
            JSONObject param = new JSONObject();
            String uuid = UUID.randomUUID().toString();
            param.set("uuid", uuid);
            try {
                num ++;
                Thread.sleep(300);
                log.info("第[{}]入参uuid="+uuid,num);
                String result = HttpUtil.post("https://dwx-sit.cpic.com.cn/vehicle/new/api/testdemo/user/testApi",param.toString());
                if (uuid.equals(result)){
                    succ ++;
                }else {
                    fail ++;
                }
                log.info("第[{}]请求返回值="+result,num);
                double bi = NumberUtil.div(succ,num,2);
                String biStr =  (bi * 100) + "%";
                log.info("第[{}]次请求,成功次数[{}],失败次数[{}],成功率[{}]",num,succ,fail,biStr);
            }catch (Exception e){
                log.error("请求异常uuid="+uuid,e);
            }

        }

    }



}
