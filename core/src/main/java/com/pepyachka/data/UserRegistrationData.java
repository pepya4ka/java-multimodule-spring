package com.pepyachka.data;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationData
{

  String email;
  String password;
}
