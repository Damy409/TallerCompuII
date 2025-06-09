package org.demo1.controller;

import org.demo1.model.Permission;
import org.demo1.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> findById(@PathVariable UUID id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        return permission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Permission create(@RequestBody Permission permission) {
        return permissionRepository.save(permission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(@PathVariable UUID id, @RequestBody Permission permission) {
        return permissionRepository.findById(id)
                .map(existing -> {
                    existing.setCode(permission.getCode());
                    existing.setName(permission.getName());
                    existing.setDescription(permission.getDescription());
                    return ResponseEntity.ok(permissionRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        return permissionRepository.findById(id)
                .map(existing -> {
                    permissionRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
