package com.example.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SimpleApplication.class, args);
		String[] beans = ctx.getBeanDefinitionNames();
		Arrays.stream(beans).sorted().forEach(System.out::println);
	}

}
