package com.pepyachka.core.service.impl;

import com.pepyachka.core.data.UserRegistrationData;
import com.pepyachka.core.model.User;
import com.pepyachka.core.repository.UserRepository;
import com.pepyachka.core.service.UserService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

  private static final String DEFAULT_USER_ROLE = "ROLE_USER";

  private final UserRepository userRepository;

  @Override
  public User save(UserRegistrationData userRegistrationData) {
    User user = User.builder()
        .email(userRegistrationData.getEmail())
        .passwordHash(userRegistrationData.getPassword())
        .role(DEFAULT_USER_ROLE)
        .createdAt(LocalDateTime.now())
        .build();

    return userRepository.save(user);
  }

  @Override
  public User getUserById(UUID userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
  }

  @Transactional(readOnly = true)
  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
  }

  @Transactional(readOnly = true)
  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
}
