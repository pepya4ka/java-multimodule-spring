package com.pepyachka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@TestConfiguration(proxyBeanMethods = false)
public class PostgresContainerConfiguration {

	@Value("${postgres.init:#{null}}")
	String resourceName;

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		PostgreSQLContainer<?> postgreSQLContainer =
						new PostgreSQLContainer<>(DockerImageName.parse("postgres:18"))
										.withDatabaseName("soup")
										.withUsername("soup")
										.withPassword("soup");

		if (resourceName != null) {
			postgreSQLContainer.withCopyFileToContainer(
							MountableFile.forClasspathResource(resourceName),
							"/docker-entrypoint-initdb.d/init.sql"
			);
		}

		return postgreSQLContainer;
	}
}
