package com.rei.javaDemo.spring;

import cn.hutool.core.text.StrBuilder;
import com.rei.javaDemo.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 异常处理
 * spring mvc提供了ControllerAdvice注解对异常进行统一的处理
 * @author Admin
 * @date 2019年9月2日18:12:59
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {


    /**
     * 这里统一处理参数异常可根据自己需要封装返回信息
     * 当不符合注解上的验证规则，会被该方法捕获到
     *
     * @param ex 方法参数异常
     * @return BaseResponse
     */
    @ExceptionHandler
    @ResponseBody
    public BaseResponse handleParamsException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        StrBuilder errorMsg = new StrBuilder();
        errorMsg.append("param exception ");
        for (FieldError error : errors) {
            errorMsg.append(error.getField());
            errorMsg.append(":");
            errorMsg.append(error.getDefaultMessage());
            errorMsg.append("; ");
        }

        return new BaseResponse(BaseResponse.PARAM_ERROR, errorMsg.toString());
    }

    /**
     * 处理方法的顶级异常信息，可以处理事务等等
     * 比如我controller里面的1/0异常就会被该方法捕获到
     * controller里面就不再捕获了
     *
     * @param ex 异常
     * @return BaseResponse
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse handleParamsException(Exception ex) {
        log.error("system error", ex);
        return new BaseResponse(BaseResponse.SYSTEM_ERROR, ex.getMessage());
    }
}