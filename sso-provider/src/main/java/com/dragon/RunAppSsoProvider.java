package com.dragon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.dragon.mapper")
@EnableEurekaClient // Eureka客户端
public class RunAppSsoProvider {

	public static void main(String[] args) {

		SpringApplication.run(RunAppSsoProvider.class, args);
	}

}
