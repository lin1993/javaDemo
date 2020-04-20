package com.rei.javaDemo.controller;

import com.rei.javaDemo.model.BaseResponse;
import com.rei.javaDemo.model.ParamTestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("param")
public class ParamController {

    @PostMapping("get")
    public BaseResponse get(@RequestBody @Valid ParamTestModel testModel){

        return new BaseResponse(testModel);
    }
}
