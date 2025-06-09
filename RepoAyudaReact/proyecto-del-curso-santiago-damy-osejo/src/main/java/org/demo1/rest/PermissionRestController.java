package org.demo1.rest;

import jakarta.validation.Valid;
import org.demo1.model.Permission;
import org.demo1.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/permission")
@Tag(name = "Permission", description = "Operaciones relacionadas con permisos")
public class PermissionRestController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionRestController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo permiso") 
    public ResponseEntity<Permission> createPermission(@RequestBody @Valid Permission permission) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.save(permission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un permiso existente")
    public ResponseEntity<Permission> updatePermission(@PathVariable UUID id, @RequestBody Permission permission) {
        try {
            if (permission.getId() == null || !permission.getId().equals(id)) {
                throw new IllegalArgumentException("El ID del path y el del cuerpo deben coincidir.");
            }
            return ResponseEntity.ok(permissionService.save(permission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un permiso por su ID") 
    public ResponseEntity<Permission> getPermissionById(@PathVariable UUID id) {
        try {
            Permission permission = permissionService.findById(id);
            if (permission != null) {
                return ResponseEntity.ok(permission);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los permisos") 
    public ResponseEntity<List<Permission>> getAllPermissions() {
        try {
            return ResponseEntity.ok(permissionService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un permiso por su ID") 
    public ResponseEntity<Object> deletePermissionById(@PathVariable UUID id) {
        try {
            Permission permission = permissionService.findById(id);
            if (permission != null) {
                permissionService.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


