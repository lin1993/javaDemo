package com.rei.javaDemo.model;

import lombok.Data;

import java.util.Date;

/**
 * 用于测试入参格式的自动转换
 * 这里列举常用的几种
 */
@Data
public class ParamTestModel {
    private String name;

    private Integer age;

    private Long number;

    private Date birth;
}
