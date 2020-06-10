package com.example.core.identical.bean;

import com.example.core.identical.bean.config.ConfigOne;
import com.example.core.identical.bean.config.ConfigTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ConfigOne.class, ConfigTwo.class})
public class IdenticalBean {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationCtx = SpringApplication.run(IdenticalBean.class);
        System.out.println(applicationCtx.getBean("example"));
    }
}
