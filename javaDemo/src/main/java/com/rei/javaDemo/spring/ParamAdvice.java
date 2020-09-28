package com.rei.javaDemo.spring;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.rei.javaDemo.utils.EncryptionUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

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
        Object jiami = httpInputMessage.getHeaders().get("jiami");
        if (jiami != null){
            String read = IoUtil.read(httpInputMessage.getBody(),"UTF-8");
            String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJC2Pgnxmo7DwpDEnyMH857QP4WL/jK/Oqmg23kW11UmU0RjOHD3kDdbobLyWbXNKU5eGYImN13s3dNeUH09NZpIjdF2IYIqUdVbM3PmkbGy4KgGRBhc0scsL2CdfPIRiSHOrSh8bQJ1qImANMeFTZ8bNysd+IwB+5RZaw7YP6dDAgMBAAECgYEAjeBu0Jb0HjYlQoPTNagtHK41KPyImz5LCFWx93UCZvQtPOY+RtsgpHWNAgGDHK/paHDvd196dvaswPc92JuzOXckzjQUKtpS/oetnmkcIgnWu2VWSz4m0rlBPixI9bBhMCcf3qBEvg2MrH19EgpOR41sqGVM94bYmWdzgfAZkkkCQQDLIyBIeDUi0mq1Z+Hal57dc0CVi8MzGfUjcyjQxVlGd9gNnSrGuwT0/CXrnaUU7lPJbgQE1B6/WAWx8H/jK98tAkEAtl7bhPqKPs2/XbUjiWchPq4hmaGuDcWtmQyCxvSWfS4Pa2SeFU3I+yiFqTsEPabGtXmOXLa2lEeXf8GRcvUmLwJAfDDedM/hFIf2Ky/2mdCmlJb4vjTzQxO0lla6dmChAv8T/MbAtzzxe+GIQXNg0NogKX70QAt7PNpQqQfy9+0DhQJBAItHvte8kMCOWkyy/sT9ooJAcyDmPgv+oSp2R5qzSguoldYrLiCPG6PVut8YV5DOQoxuna9pS9/LHYVQzEjDubkCQC1hMMNnFDbxjT8ObTOLU/rWsg21P8EIX5Jr5j0bXcU3knd+MaoVFeRDmTbEo6UGBFAjCrVbaE4u1oN033wTmoE=";
            String encStr = EncryptionUtil.decryptRSA(read,privateKey);
            return new MappingJacksonInputMessage(Objects.requireNonNull(IoUtil.toStream(encStr, "UTF-8")),httpInputMessage.getHeaders());
        }

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
