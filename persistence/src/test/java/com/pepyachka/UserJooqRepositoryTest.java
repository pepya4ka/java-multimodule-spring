package com.pepyachka;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepyachka.config.PersistenceConfig;
import com.pepyachka.config.PostgresContainerConfiguration;
import com.pepyachka.model.User;
import com.pepyachka.repository.UserRepository;
import com.pepyachka.util.ResourceLoader;

@JooqTest
@Import(PostgresContainerConfiguration.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(
				scripts = "classpath:clean-up.sql",
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UserJooqRepositoryTest
{

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	private UserRepository repository;

	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
					"classpath:clean-up.sql",
					"classpath:insert-users.sql"
	})
	void shouldFindUserById() throws JsonProcessingException
	{
		UUID userId = UUID.fromString("c0a38c77-b833-43e8-b563-0d8dc13d4cb6");

		Optional<User> found = repository.findById(userId);

		assertThat(found).isPresent();

		String actualJson = objectMapper.writeValueAsString(List.of(found.get()));
		String expectedJson = ResourceLoader.loadResourceAsString("fixtures/list-users.json");

		assertThatJson(actualJson).isEqualTo(expectedJson);
	}

	@Test
	void shouldReturnEmptyForNonexistentUser()
	{
		Optional<User> found = repository.findById(UUID.randomUUID());
		assertThat(found).isEmpty();
	}
}
