package com.wkl.myclientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients
public class MyclientOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyclientOneApplication.class, args);
	}

}
