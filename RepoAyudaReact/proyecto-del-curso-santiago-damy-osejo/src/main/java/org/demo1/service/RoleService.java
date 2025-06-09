package org.demo1.service;

import org.demo1.exception.RoleNotFoundException;
import org.demo1.model.Role;
import org.demo1.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("El ID del rol no puede ser nulo para la actualización.");
        }
        return roleRepository.save(role);
    }

    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("No se puede eliminar. Rol con ID " + id + " no encontrado.");
        }
        roleRepository.deleteById(id);
    }
}
