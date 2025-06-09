package org.demo1.repository;

import java.util.Optional;
import java.util.UUID;

import org.demo1.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    
    Optional<UserProfile> findByUserId(UUID userId);
}
