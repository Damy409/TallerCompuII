package org.demo1.mappers;

import org.demo1.dtos.RoleOutDto;
import org.demo1.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface RoleMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "code", target = "code"),
        @Mapping(source = "permissions", target = "permissions")
    })
    RoleOutDto toDto(Role role);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "code", target = "code"),
        @Mapping(source = "permissions", target = "permissions")
    })
    Role toEntity(RoleOutDto dto);
}
