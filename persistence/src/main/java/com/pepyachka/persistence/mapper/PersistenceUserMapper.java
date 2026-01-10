package com.pepyachka.persistence.mapper;

import com.pepyachka.core.model.User;
import com.pepyachka.persistence.jooq.tables.records.UsersRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR
)
public interface PersistenceUserMapper {

  User toModel(UsersRecord usersRecord);

  @Mapping(target = "id", ignore = true)
  UsersRecord toRecord(User user);
}
