package com.example.demo;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling

@MapperScan("com.example.demo.mapper")
@Component
public class SpringbootMybatisDemo2Application {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisDemo2Application.class,args);
	}

}
