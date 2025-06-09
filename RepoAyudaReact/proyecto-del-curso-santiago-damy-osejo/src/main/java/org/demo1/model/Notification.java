package org.demo1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 250, nullable = false)
    private String type;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 4000, nullable = false)
    private String description;

    @Column(nullable = false)
    private UUID userId;
}
