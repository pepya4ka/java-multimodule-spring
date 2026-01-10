package com.pepyachka.core.repository;

import com.pepyachka.core.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  Optional<User> findById(UUID id);

  List<User> findAll();

  User save(User user);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
