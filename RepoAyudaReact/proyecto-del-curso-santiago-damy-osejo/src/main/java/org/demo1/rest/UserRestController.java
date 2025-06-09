package org.demo1.rest;

import jakarta.validation.Valid;
import org.demo1.model.User;
import org.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@Tag(name = "User", description = "Operaciones relacionadas con usuarios")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario") 
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody @Valid User user) {
        return userService.findById(id).map(existingUser -> {
            user.setId(id); // Asegura que se actualice el usuario correcto
            return ResponseEntity.ok(userService.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID") 
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok) // El map() está bien aquí
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios") 
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por su ID")
    public ResponseEntity<Object> deleteUserById(@PathVariable UUID id) {
        return userService.findById(id).map(user -> {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}







