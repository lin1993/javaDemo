package com.rei.javaDemo.service;

import com.rei.javaDemo.example.BusException;

public interface TestService {
    /**
     * 数据库的相关操作演示
     */
    void dateBaseTest();

    /**
     * 自定义异常
     */
    void dateException() throws BusException;

    /**
     * 注解切面相关测试
     * @throws BusException
     */
    String aspectTest();
}
