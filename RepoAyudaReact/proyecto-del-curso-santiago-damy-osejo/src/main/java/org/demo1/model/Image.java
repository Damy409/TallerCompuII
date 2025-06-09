package org.demo1.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "dmt_image")

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

}
