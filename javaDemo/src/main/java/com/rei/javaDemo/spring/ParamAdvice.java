package com.rei.javaDemo.spring;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 参数的前置处理
 */
@Slf4j
@ControllerAdvice
public class ParamAdvice implements RequestBodyAdvice {

    /**
     * 判断要不要执行前置过滤
     * @param methodParameter
     * @param type
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 参数读取之前
     * @param httpInputMessage
     * @param methodParameter
     * @param type
     * @param aClass
     * @return
     * @throws IOException
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
//        JSONObject requestBody = JSON.parseObject(IoUtil.getReader(httpInputMessage.getBody(),"UTF-8").toString());

        return httpInputMessage;
    }

    /**
     * 参数读取之后
     * @param o
     * @param httpInputMessage
     * @param methodParameter
     * @param type
     * @param aClass
     * @return
     */
    @SneakyThrows
    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {


        JSONObject json = (JSONObject) JSONObject.toJSON(o);
        if (StrUtil.isNotBlank(json.getString("name"))){
            json.put("name","被我处理过的");
        }
        if (true){
//            throw  new Exception("yichangtoutou");
        }

        //
        return json.toJavaObject(type);
    }

    /**
     * 无请求时的处理
     * @param o
     * @param httpInputMessage
     * @param methodParameter
     * @param type
     * @param aClass
     * @return
     */
    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }
}
