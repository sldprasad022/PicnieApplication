package com.techpixe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techpixe.controller," + "com.techpixe.service," + "com.techpixe.serviceImpl")
public class PicnieApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicnieApplication.class, args);
	}

}
