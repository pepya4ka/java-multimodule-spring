package com.pepyachka.config;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
				"com.pepyachka.persistence",
				"com.pepyachka.mapper",
				"com.pepyachka"
})
@Import(JacksonAutoConfiguration.class)
public class PersistenceConfig
{
}
