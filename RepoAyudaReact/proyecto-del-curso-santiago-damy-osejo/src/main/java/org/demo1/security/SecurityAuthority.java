package org.demo1.security;

import org.demo1.model.Permission;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Permission permission;

    @Override
    public String getAuthority() {
        return permission.getName(); // Asegúrate de tener este método en tu entidad Permission
    }
}
