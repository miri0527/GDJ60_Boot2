package com.iu.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling //메인메서드에 Schedule 기능 사용 선언
@SpringBootApplication
public class Gdj60Boot2Application {

	public static void main(String[] args) {
		SpringApplication.run(Gdj60Boot2Application.class, args);
	}

}
