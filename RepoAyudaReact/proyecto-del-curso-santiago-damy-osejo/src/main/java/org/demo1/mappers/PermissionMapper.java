package org.demo1.mappers;

import org.demo1.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    default String toDto(Permission permission) {
        return permission.getName();
    }

    default Permission toEntity(String name) {
        Permission permission = new Permission();
        permission.setName(name);
        return permission;
    }
}
