package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 这是一个EurekaServer?
public class RunAppEureka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(RunAppEureka.class, args);
	}

}
