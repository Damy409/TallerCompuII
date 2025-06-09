package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_state")
public class State {

    @Id
    @Column(name = "stateId", length = 4000, nullable = false)
    private String stateId;

    @Column(name = "stateName", length = 4000, nullable = false)
    private String stateName;

}
