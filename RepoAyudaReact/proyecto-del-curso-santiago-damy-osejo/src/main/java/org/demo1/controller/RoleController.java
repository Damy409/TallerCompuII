package org.demo1.controller;

import org.demo1.model.Role;
import org.demo1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable UUID id, @RequestBody Role role) {
        return roleRepository.findById(id)
                .map(existing -> {
                    existing.setName(role.getName());
                    existing.setCode(role.getCode());
                    existing.setPermissions(role.getPermissions());
                    return ResponseEntity.ok(roleRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        return roleRepository.findById(id)
                .map(existing -> {
                    roleRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
