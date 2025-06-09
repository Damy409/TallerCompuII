package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_purchase")
public class Purchase {

    @Id
    @Column(name = "purchaseId", length = 100, nullable = false)
    private String purchaseId;

    @Column(name = "purchaseDate", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "post", referencedColumnName = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "userId", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer", referencedColumnName = "userId", nullable = false)
    private User buyer;

}
