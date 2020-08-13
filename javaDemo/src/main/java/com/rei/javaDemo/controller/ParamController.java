package com.rei.javaDemo.controller;

import com.rei.javaDemo.example.BusException;
import com.rei.javaDemo.mapper.TestMapper;
import com.rei.javaDemo.model.BaseResponse;
import com.rei.javaDemo.model.ParamTestModel;
import com.rei.javaDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("param")
public class ParamController {

    @Autowired
    private TestService testService;

    @Value("${scdule.time:123}")
    private String time;

    @PostMapping("get")
    public BaseResponse get(@RequestBody @Valid ParamTestModel testModel) throws BusException {

        testService.dateException();

        try {
            System.out.println(time);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        testService.dateBaseTest();
        return new BaseResponse(testModel);
    }
}
