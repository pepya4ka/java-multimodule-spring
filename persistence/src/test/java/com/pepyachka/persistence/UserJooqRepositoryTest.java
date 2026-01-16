package com.pepyachka.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.pepyachka.core.model.User;
import com.pepyachka.core.repository.UserRepository;
import com.pepyachka.persistence.config.PersistenceConfig;
import com.pepyachka.persistence.config.PostgresContainerConfiguration;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@JooqTest
@Import(PostgresContainerConfiguration.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(
    scripts = "classpath:clean-up.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UserJooqRepositoryTest {

  @Autowired
  private UserRepository repository;

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
      "classpath:clean-up.sql",
      "classpath:insert-users.sql"
  })
  void shouldFindUserById() {
    UUID userId = UUID.fromString("c0a38c77-b833-43e8-b563-0d8dc13d4cb6");

    Optional<User> found = repository.findById(userId);

    assertThat(found).isPresent().get()
        .satisfies(user -> {
          assertThat(user.getId()).isEqualTo(userId);
          assertThat(user.getEmail()).isEqualTo("test@soup.com");
        });
  }

  @Test
  void shouldReturnEmptyForNonexistentUser() {
    Optional<User> found = repository.findById(UUID.randomUUID());

    assertThat(found).isEmpty();
  }
}
