package com.pepyachka.mapper;

import com.pepyachka.data.UserRegistrationData;
import com.pepyachka.dto.UserDto;
import com.pepyachka.dto.UserRegistrationRequest;
import com.pepyachka.model.User;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR,
    implementationName = "ApiUserMapperImpl"
)
public interface UserMapper {

  UserRegistrationData toRegistrationData(UserRegistrationRequest apiRequest);

  UserDto toDto(User user);
}
