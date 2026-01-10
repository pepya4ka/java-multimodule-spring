package com.pepyachka.api.rest;

import com.pepyachka.api.dto.UserDto;
import com.pepyachka.api.dto.UserRegistrationRequest;
import com.pepyachka.api.mapper.UserMapper;
import com.pepyachka.core.data.UserRegistrationData;
import com.pepyachka.core.model.User;
import com.pepyachka.core.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserMapper mapper;
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(
      @Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
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
