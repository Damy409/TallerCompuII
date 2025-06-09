package org.demo1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import org.demo1.dtos.UsersOutDto;
import org.demo1.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "lastname", target = "lastname"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "password", target = "password"),
        @Mapping(source = "roles", target = "roles")
    })
    UsersOutDto toDto(User user);

    User toEntity(UsersOutDto userDto);

    @Named("userTransform")
    default String userTransform(String user) {
        return user.toUpperCase();
    }
}
