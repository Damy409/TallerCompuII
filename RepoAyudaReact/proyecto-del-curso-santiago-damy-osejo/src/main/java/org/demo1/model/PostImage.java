package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_post_image")
public class PostImage {

    @Id
    @Column(name = "postImageId", length = 4000, nullable = false)
    private String postImageId;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    private Image image;
}
