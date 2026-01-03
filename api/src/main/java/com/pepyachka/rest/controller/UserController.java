package com.pepyachka.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pepyachka.data.UserRegistrationData;
import com.pepyachka.dto.UserDto;
import com.pepyachka.dto.UserRegistrationRequest;
import com.pepyachka.mapper.UserMapper;
import com.pepyachka.model.User;
import com.pepyachka.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController
{
	private final UserMapper mapper;
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest)
	{
		UserRegistrationData registrationData = mapper.toRegistrationData(userRegistrationRequest);
		User user = userService.save(registrationData);

		UserDto response = mapper.toDto(user);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/check-user")
	public ResponseEntity<Boolean> checkUser(@RequestParam("email") String email) {
		boolean userExists = userService.existsByEmail(email);
		return ResponseEntity.ok(userExists);
	}
}
