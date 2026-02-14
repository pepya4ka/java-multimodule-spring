package com.pepyachka.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.pepyachka.core.model.User;
import com.pepyachka.core.repository.UserRepository;
import com.pepyachka.persistence.config.PersistenceConfig;
import com.pepyachka.persistence.config.PostgresContainerConfiguration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jooq.test.autoconfigure.JooqTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@JooqTest
@Import(PostgresContainerConfiguration.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(
    scripts = {
        "classpath:clean-up.sql",
        "classpath:insert-users.sql"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class UserJooqRepositoryTest {

  private static final UUID USER_ID = UUID.fromString("c0a38c77-b833-43e8-b563-0d8dc13d4cb6");
  private static final LocalDateTime CREATED_AT_USER = LocalDateTime.of(2025, 12, 22, 0, 0, 0,10_000_000);


  @Autowired
  private UserRepository repository;

  @Test
  void shouldFindById() {
    User expected = User.builder()
        .id(USER_ID)
        .email("test@soup.com")
        .passwordHash("hashed-password")
        .role("USER")
        .createdAt(CREATED_AT_USER)
        .build();

    Optional<User> result = repository.findById(USER_ID);

    assertThat(result).isPresent();
    assertThat(result.get())
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  void shouldReturnEmptyWhenUserDoesNotExist() {
    Optional<User> result = repository.findById(UUID.randomUUID());

    assertThat(result).isEmpty();
  }
}
