package com.babytrak24;

/**
 *  SpringBootConfig for BabyTrak24App, adding all controllers for app, including RESTControllers
 */
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = { "com.babytrak24.controller" })
@ComponentScan
@EnableWebMvc
public class SpringBootConfig extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootConfig.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootConfig.class, args); // it will start application
	}
}
