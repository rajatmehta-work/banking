package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com.banking","com.banking.config"})
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableWebMvc

public class BankingApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(BankingApplication.class, args);
    }
}