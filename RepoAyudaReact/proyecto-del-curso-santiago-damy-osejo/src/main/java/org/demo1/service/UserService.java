package org.demo1.service;

import org.demo1.exception.UserNotFoundException;
import org.demo1.model.User;
import org.demo1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByName(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Usuario con nombre " + username + " no encontrado.");
        }
        return userOptional;
    }

    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No se puede eliminar. Usuario con ID " + id + " no encontrado.");
        }
        userRepository.deleteById(id);
    }

     public Optional<User> findByEmail(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Usuario con nombre " + username + " no encontrado.");
        }
        return userOptional;
    }
}
