package com.rei.javaDemo.example;

public class BusException extends Exception {

    private int code;

    private String msg;

    public BusException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusException(int code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}
