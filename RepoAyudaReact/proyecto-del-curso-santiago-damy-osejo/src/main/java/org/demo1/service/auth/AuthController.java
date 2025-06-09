package org.demo1.service.auth;

import org.demo1.security.JwtService;
import org.demo1.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            User user = authService.authenticateUser(request);
            String token = jwtService.generateAccessToken(user, null);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            e.printStackTrace();  // log to console or use logger.error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("¡Hola desde el endpoint protegido!");
    }
}
