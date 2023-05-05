package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String comment;

    @OneToMany(
            targetEntity = PurchaseItem.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PurchaseItem> items = new ArrayList<>();
    private Double total;


}
