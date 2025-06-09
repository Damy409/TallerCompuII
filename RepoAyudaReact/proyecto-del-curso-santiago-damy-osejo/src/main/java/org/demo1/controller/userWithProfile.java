package org.demo1.controller;

import lombok.RequiredArgsConstructor;
import org.demo1.dtos.UserWithProfileDTO;
import org.demo1.model.Image;
import org.demo1.model.User;
import org.demo1.model.UserProfile;
import org.demo1.repository.ImageRepository;
import org.demo1.repository.UserProfileRepository;
import org.demo1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class userWithProfile {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/with-profile")
    public String createUserWithProfile(@RequestBody UserWithProfileDTO dto) {
        // Create User
        User user = new User();
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        // Create Image
        Image image = new Image();
        image.setName(dto.getImageName());
        image.setType(dto.getImageType());
        image.setData(dto.getImageData());
        Image savedImage = imageRepository.save(image);

        // Create Profile
        UserProfile profile = new UserProfile();
        profile.setDescription(dto.getProfileDescription());
        profile.setUser(savedUser);
        profile.setImage(savedImage);

        userProfileRepository.save(profile);

        return "User and profile created successfully.";
    }
}
