package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_favorite")
public class Favorite {

    @Id
    @Column(name = "favoriteId", length = 4000, nullable = false)
    private String favoriteId;

    @ManyToOne
    @JoinColumn(name = "favorite_user", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "favorite_post", referencedColumnName = "postId", nullable = false)
    private Post post;
}
