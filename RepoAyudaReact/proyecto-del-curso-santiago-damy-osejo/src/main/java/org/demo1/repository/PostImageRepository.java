package org.demo1.repository;
import org.demo1.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostImageRepository extends JpaRepository<PostImage, UUID> {

}
