package com.ssafy.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BootSaveHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSaveHomeApplication.class, args);
		
	}

}
