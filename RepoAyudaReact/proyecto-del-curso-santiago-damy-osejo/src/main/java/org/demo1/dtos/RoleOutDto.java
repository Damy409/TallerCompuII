package org.demo1.dtos;

import java.util.Set;

import org.demo1.model.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleOutDto {
    private String id;
    private String code;
    private String name;
    private Set<Permission> permissions;
    
}
