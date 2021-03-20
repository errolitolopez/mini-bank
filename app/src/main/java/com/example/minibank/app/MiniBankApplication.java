package com.example.minibank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.example.minibank")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EntityScan("com.example.minibank.data.persistence.entity")
@EnableJpaRepositories(basePackages = "com.example.minibank.data.persistence.repository")
public class MiniBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniBankApplication.class, args);
    }
}
