package com.rei.javaDemo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用于测试入参格式的自动转换
 * 这里列举常用的几种
 * @NotEmpty 用在集合类上面
 *
 * @NotBlank 用在String上面
 *
 * @NotNull 用在基本类型上以及对象上，不过不建议用对象，对象请转string处理
 */
@Data
public class ParamTestModel {

    @NotBlank(message = "姓名不能为空,NotBlank可以判空或者空字符串")
    private String name;
    // 基本类型用notnull
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotNull(message = "数字不能为空")
    private Long number;

    @NotNull(message = "年龄不能为空")
    private Date birth;
}
