package org.demo1.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "code", length = 250, nullable = false)
    private String code;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
