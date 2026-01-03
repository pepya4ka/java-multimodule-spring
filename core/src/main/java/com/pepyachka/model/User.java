package com.pepyachka.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User
{

  UUID id;
  String email;
  String passwordHash;
  String role;
  LocalDateTime createdAt;
}
