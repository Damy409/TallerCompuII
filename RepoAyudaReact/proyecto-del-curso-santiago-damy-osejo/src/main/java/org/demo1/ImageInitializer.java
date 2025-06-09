package org.demo1;

import org.demo1.model.Image;
import org.demo1.model.Post;
import org.demo1.model.User;
import org.demo1.model.UserProfile;
import org.demo1.repository.ImageRepository;
import org.demo1.repository.PostImageRepository;
import org.demo1.repository.PostRepository;
import org.demo1.repository.UserProfileRepository;
import org.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Configuration
public class ImageInitializer {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostImageRepository postImageRepository;

    @Bean
    public ApplicationRunner loadImagesOnStartup() {
        return args -> {


            Optional<User> user = userRepository.findByName("John");
            

            if(user.isEmpty()) {

            List<Path> imagePaths = List.of(
                Path.of("src/main/resources/images/saul-goodman.jpg"),
                Path.of("src/main/resources/images/saul-goodman2.jpg")
                // Add more images as needed
            );

            for (Path path : imagePaths) {
                try {
                    byte[] data = Files.readAllBytes(path);

                    Image image = new Image();
                    image.setName(path.getFileName().toString());
                    image.setType(Files.probeContentType(path));
                    image.setData(data);

                    imageRepository.save(image);
                    System.out.println("Saved image: " + image.getName());

                } catch (IOException e) {
                    System.err.println("Failed to load image from path: " + path);
                    e.printStackTrace();
                }
            }

            User user1 = userRepository.findByName("Jhon").orElse(null);
            User user2 = userRepository.findByName("Jane").orElse(null);

            if (user1 != null) {
                UserProfile userProfile1 = new UserProfile();
                userProfile1.setUser(user1);
                userProfile1.setDescription("Bio for Jhon");
                userProfile1.setImage(imageRepository.findById(Long.valueOf(1)).orElse(null));
                userProfileRepository.save(userProfile1);
            }

            if (user2 != null) {
                UserProfile userProfile2 = new UserProfile();
                userProfile2.setUser(user2);
                userProfile2.setDescription("Bio for Jane");
                userProfile2.setImage(imageRepository.findById(Long.valueOf(2)).orElse(null));
                userProfileRepository.save(userProfile2);
            }
        };

        };
    }
}
