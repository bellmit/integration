package com.qunjie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@MapperScan({"com.qunjie.**.mapper"})
@EnableScheduling
@EnableAsync
public class AxisApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AxisApplication.class, args);
	}

}
