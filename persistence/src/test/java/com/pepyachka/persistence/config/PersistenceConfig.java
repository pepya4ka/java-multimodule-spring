package com.pepyachka.persistence.config;

import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
    "com.pepyachka.persistence"
})
@Import(JacksonAutoConfiguration.class)
public class PersistenceConfig {

}
