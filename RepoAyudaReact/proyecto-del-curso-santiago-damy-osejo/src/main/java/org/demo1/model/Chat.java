package org.demo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dmt_chat")
public class Chat {

    @Id
    @Column(name = "chatId", length = 4000, nullable = false)
    private String chatId;

    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "userId", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer", referencedColumnName = "userId", nullable = false)
    private User buyer;

    @Lob
    @Column(name = "json_data", columnDefinition = "TEXT")
    private String jsonData;
}
