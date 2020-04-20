package com.rei.javaDemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private Integer code;

    private String msg;

    private Object data;

    public static int PARAM_ERROR = 1001;
    public static int SYSTEM_ERROR = 1002;
    public static String PARAM_ERROR_MSG = "参数异常";

    public BaseResponse (){
        this.code = 0;
        this.msg = "执行成功";
    }
    public BaseResponse (Object obj){
        this.code = 0;
        this.msg = "执行成功";
        this.data = obj;
    }
    public BaseResponse (int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
