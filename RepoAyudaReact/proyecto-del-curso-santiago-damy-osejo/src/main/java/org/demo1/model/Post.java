package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_post")
public class Post {

    @Id
    @Column(name = "postId", length = 4000, nullable = false)
    private String postId;

    @Column(name = "postName", length = 100, nullable = false)
    private String postName;

    @Column(name = "description", length = 4000, nullable = false)
    private String description;

    @Column(name = "itemPrice", nullable = false)
    private BigDecimal itemPrice;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "publicationDate")
    private LocalDate publicationDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "stateId")
    private State state;

    @ManyToOne
    @JoinColumn(name = "post_user", referencedColumnName = "userId")
    private User user;
}
