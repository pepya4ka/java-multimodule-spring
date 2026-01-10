package com.pepyachka;

import com.pepyachka.mapper.PersistenceUserMapper;
import com.pepyachka.model.User;
import com.pepyachka.persistence.jooq.tables.Users;
import com.pepyachka.persistence.jooq.tables.records.UsersRecord;
import com.pepyachka.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class UserJooqRepository implements UserRepository {

  private final DSLContext dsl;
  private final PersistenceUserMapper mapper;

  @Override
  public Optional<User> findById(UUID id) {
    return dsl.selectFrom(Users.USERS)
        .where(Users.USERS.ID.eq(id))
        .fetchOptional()
        .map(mapper::toModel);
  }

  @Override
  public List<User> findAll() {
    return dsl.selectFrom(Users.USERS)
        .fetch()
        .map(mapper::toModel);
  }

  @Transactional
  @Override
  public User save(User user) {
    UsersRecord usersRecord = mapper.toRecord(user);

    UsersRecord record = dsl.insertInto(Users.USERS)
        .set(usersRecord)
        .onConflict(Users.USERS.ID)
        .doUpdate()
        .set(usersRecord)
        .returning()
        .fetchOne();

    return mapper.toModel(record);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return dsl.selectFrom(Users.USERS)
        .where(Users.USERS.EMAIL.eq(email))
        .fetchOptional()
        .map(mapper::toModel);
  }

  @Transactional(readOnly = true)
  @Override
  public boolean existsByEmail(String email) {
    return dsl.fetchExists(
        dsl.selectFrom(Users.USERS)
            .where(Users.USERS.EMAIL.eq(email))
    );
  }
}
