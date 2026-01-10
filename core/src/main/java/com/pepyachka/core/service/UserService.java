package com.pepyachka.core.service;

import com.pepyachka.core.data.UserRegistrationData;
import com.pepyachka.core.model.User;
import java.util.UUID;

public interface UserService {

  User save(UserRegistrationData userRegistrationData);

  User getUserById(UUID userId);

  User getUserByEmail(String email);

  boolean existsByEmail(String email);
}
