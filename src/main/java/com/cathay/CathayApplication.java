package com.cathay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling	//啟用定時任務的配置
public class CathayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CathayApplication.class, args);
	}

}
