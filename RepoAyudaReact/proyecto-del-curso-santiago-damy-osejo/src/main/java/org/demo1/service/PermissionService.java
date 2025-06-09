package org.demo1.service;

import org.demo1.exception.PermissionNotFoundException;
import org.demo1.model.Permission;
import org.demo1.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission update(Permission permission) {
        if (permission.getId() == null) {
            throw new IllegalArgumentException("El ID del permiso no puede ser nulo para la actualización.");
        }
        return permissionRepository.save(permission);
    }

    public Permission findById(UUID id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permiso con ID " + id + " no encontrado."));
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!permissionRepository.existsById(id)) {
            throw new PermissionNotFoundException("No se puede eliminar. Permiso con ID " + id + " no encontrado.");
        }
        permissionRepository.deleteById(id);
    }
}
