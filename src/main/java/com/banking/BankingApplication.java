package com.banking;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"com.pismo.banking"})
@EnableAsync
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@EnableScheduling

public class BankingApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(BankingApplication.class, args);
    }
}