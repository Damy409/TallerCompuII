package org.demo1.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_user")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 250, nullable = false, unique = true)
    private String name;

    @Column(name = "last_name", length = 250, nullable = false)
    private String lastname;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "email", length = 250, nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "dmt_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email; // Retorna el correo como nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes personalizar esto con un campo `accountExpired`
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes personalizar esto con un campo `accountLocked`
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes personalizar esto con un campo `credentialsExpired`
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes personalizar esto con un campo `enabled`
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
