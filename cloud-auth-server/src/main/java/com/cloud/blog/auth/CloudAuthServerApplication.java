package com.cloud.blog.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@MapperScan(basePackages = {"com.cloud.blog.auth.mapper"})
@EnableEurekaClient
@SpringBootApplication
public class CloudAuthServerApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthServerApplication.class, args);
    }

    @PostConstruct
    public void init(){
        System.out.println(passwordEncoder.encode("Ws961226"));
    }

}
