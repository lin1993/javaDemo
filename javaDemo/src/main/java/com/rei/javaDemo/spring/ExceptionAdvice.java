package com.rei.javaDemo.spring;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.rei.javaDemo.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 异常处理
 * spring mvc提供了ControllerAdvice注解对异常进行统一的处理
 * @author Admin
 * @date 2019年9月2日18:12:59
 */
@Order(1)//指定该实体bean被加载的顺序，注解中的值越小越优先被加载注入。
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
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
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
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public BaseResponse handleParamsException(NullPointerException ex) throws IOException {
        StrBuilder errorMsg = new StrBuilder();
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0){
            List<StackTraceElement> errorList = Arrays.asList(ex.getStackTrace());
            int af = 0;
            errorList.stream().filter(e -> e.getClassName().contains("com.rei")).forEach(e ->{
                String template = "|错误信息为：{}类,{}方法,第{}行";
                errorMsg.append(StrUtil.format(template,e.getClassName(),e.getMethodName(),e.getLineNumber()));
            });
        }
        errorMsg.append("==系统发生异常==");
        log.error(errorMsg.toString());
        return new BaseResponse(BaseResponse.SYSTEM_ERROR, "系统异常3000");
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
    public BaseResponse handleParamsException(Exception ex) throws IOException {
        log.error("所有的异常信息如下：",ex);
        StrBuilder errorMsg = new StrBuilder();
        errorMsg.append("==系统发生异常==");
        if(ex.getCause() != null){
            errorMsg.append(ex.getCause().getMessage());
            errorMsg.append("|具体错误信息为:");
        }
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0){
           List<StackTraceElement> errorList = Arrays.asList(ex.getStackTrace());
           int af = 0;
            errorList.stream().filter(e -> e.getClassName().contains("com.rei")).forEach(e ->{
                String template = "|错误信息为：{}类,{}方法,第{}行";
                errorMsg.append(StrUtil.format(template,e.getClassName(),e.getMethodName(),e.getLineNumber()));
            });
        }

        log.error(errorMsg.toString());
        return new BaseResponse(BaseResponse.SYSTEM_ERROR, ex.getMessage());
    }
    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}