package org.demo1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dmt_user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
