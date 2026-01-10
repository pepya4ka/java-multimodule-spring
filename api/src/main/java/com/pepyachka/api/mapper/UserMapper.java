package com.pepyachka.api.mapper;

import com.pepyachka.api.dto.UserDto;
import com.pepyachka.api.dto.UserRegistrationRequest;
import com.pepyachka.core.data.UserRegistrationData;
import com.pepyachka.core.model.User;
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
