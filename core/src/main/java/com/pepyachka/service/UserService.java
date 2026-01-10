package com.pepyachka.service;

import com.pepyachka.data.UserRegistrationData;
import com.pepyachka.model.User;
import java.util.UUID;

public interface UserService {

  User save(UserRegistrationData userRegistrationData);

  User getUserById(UUID userId);

  User getUserByEmail(String email);

  boolean existsByEmail(String email);
}
