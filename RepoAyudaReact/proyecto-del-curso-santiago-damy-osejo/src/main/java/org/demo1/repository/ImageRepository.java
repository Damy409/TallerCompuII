package org.demo1.repository;

import java.util.Optional;

import org.demo1.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO dmt_image(name, type, data) VALUES (:name, :type, :data)", nativeQuery = true)
    void saveImg(@Param("name") String name, @Param("type") String type, @Param("data") byte[] data);

    Optional<Image> findByName(String string);


}

