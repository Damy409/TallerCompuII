package org.demo1.service.auth;

import org.demo1.model.User;

public interface AuthService {
    void registerUser(RegisterRequest request);
    User authenticateUser(LoginRequest request);

}
