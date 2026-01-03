package com.pepyachka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pepyachka.model.User;
import com.pepyachka.persistence.jooq.tables.records.UsersRecord;

@Mapper(
				componentModel = "spring",
				unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR
)
public interface PersistenceUserMapper
{

	User toModel(UsersRecord usersRecord);

	@Mapping(target = "id", ignore = true)
	UsersRecord toRecord(User user);
}
