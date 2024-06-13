
package com.banking.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
@Getter
@Setter
@ToString
@Configuration
@Slf4j
public class FlywayConfig {

    @Autowired private DatabaseConfig databaseConfig;

    @PostConstruct
    public void initialize() {
        log.info("FlywayConfig initialized with properties: " +
                ", url=" + databaseConfig.getUrl() +
                ", username=" + databaseConfig.getUsername() +
                ", password=" + databaseConfig.getPassword());
        Flyway flyway = Flyway.configure().outOfOrder(true).dataSource(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword()).load();
        flyway.migrate();
    }
}