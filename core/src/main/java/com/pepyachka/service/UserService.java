package com.pepyachka.service;

import java.util.UUID;

import com.pepyachka.data.UserRegistrationData;
import com.pepyachka.model.User;

public interface UserService
{
	User save(UserRegistrationData userRegistrationData);

	User getUserById(UUID userId);

	User getUserByEmail(String email);

	boolean existsByEmail(String email);
}
