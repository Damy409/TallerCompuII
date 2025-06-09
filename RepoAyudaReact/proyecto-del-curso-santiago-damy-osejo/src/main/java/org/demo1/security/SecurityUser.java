package org.demo1.security;

import java.util.Collection;
import java.util.stream.Collectors;
import org.demo1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUser implements UserDetails {

    private final User user;
    private boolean enabled = true;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getName(); // o getEmail(), depende de tu implementación
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(SecurityAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia según lógica real
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia según lógica real
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia según lógica real
    }

    @Override
    public boolean isEnabled() {
        return enabled; // o true si no tienes esa lógica
    }
}
