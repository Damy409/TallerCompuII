package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_category")
public class Category {

    @Id
    @Column(name = "categoryId", length = 4000, nullable = false)
    private String categoryId;

    @Column(name = "categoryName", length = 4000, nullable = false)
    private String categoryName;

}

