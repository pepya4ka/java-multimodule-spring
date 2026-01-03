package com.pepyachka.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pepyachka.model.User;

public interface UserRepository
{
	Optional<User> findById(UUID id);

	List<User> findAll();

	User save(User user);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
