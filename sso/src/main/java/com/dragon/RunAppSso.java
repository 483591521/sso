package com.dragon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class RunAppSso {

	public static void main(String[] args) {
		SpringApplication.run(RunAppSso.class, args);
	}

}
