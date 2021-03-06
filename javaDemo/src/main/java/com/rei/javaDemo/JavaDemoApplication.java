package com.rei.javaDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rei.javaDemo.mapper")
@EnableScheduling
@EnableAsync //  启动异步
public class JavaDemoApplication {

	public static void main(String[] args)
	{
		// 设置编码
		System.setProperty("LANG", "zh_CN.UTF-8");
		System.setProperty("LC_ALL", "zh_CN.UTF-8");
		System.out.println(System.getProperty("file.encoding"));
		System.out.println("===================================");
		System.getProperties().list(System.out);//输出当前环境属性
		SpringApplication.run(JavaDemoApplication.class, args);
	}

}
