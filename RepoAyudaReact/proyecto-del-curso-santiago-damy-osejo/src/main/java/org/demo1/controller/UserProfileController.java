package org.demo1.controller;

import org.demo1.model.UserProfile;
import org.demo1.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<UserProfile> getProfileByUserId(@PathVariable UUID userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);
        return userProfile.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // ... other CRUD methods
}
