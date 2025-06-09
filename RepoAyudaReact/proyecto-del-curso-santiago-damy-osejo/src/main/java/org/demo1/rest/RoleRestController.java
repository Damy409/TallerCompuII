package org.demo1.rest;

import jakarta.validation.Valid;
import org.demo1.model.Role;
import org.demo1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/role")

@Tag(name = "Role", description = "Operaciones relacionadas con roles")
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rol") 
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role role) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol existente") 
    public ResponseEntity<Role> updateRole(@PathVariable UUID id, @RequestBody Role role) {
        try {
            if (role.getId() == null || !role.getId().equals(id)) {
                throw new IllegalArgumentException("ID del path y del body deben coincidir.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(roleService.save(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un rol por su ID") 
    public ResponseEntity<Role> getRoleById(@PathVariable UUID id) {
        try {
            return roleService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los roles") 
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            return ResponseEntity.ok(roleService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol por su ID")
    public ResponseEntity<Object> deleteRoleById(@PathVariable UUID id) {
        try {
            return roleService.findById(id).map(role -> {
                roleService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}


