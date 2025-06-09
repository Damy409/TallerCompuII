package org.demo1.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.demo1.model.Role; 

@Getter
@Setter
public class UsersOutDto {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles;

    
    
}
