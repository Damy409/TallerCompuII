package org.demo1.rest;

import org.demo1.model.UserProfile;
import org.demo1.repository.UserProfileRepository;
import org.demo1.repository.UserRepository;
import org.demo1.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileRestController {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserProfileRestController(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserProfile> getUserProfileByName(@PathVariable String name) {
        Optional<User> user = userRepository.findByName(name);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get().getId());

        if (userProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userProfile.get());
    }
}
