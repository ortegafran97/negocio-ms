package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Table(name = "deposit")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column
    @Enumerated(EnumType.STRING)
    private DepositState state = DepositState.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    private LocalDateTime updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    private String comment;

    @OneToOne(targetEntity = Purchase.class)
    private Purchase purchase;

    public Deposit(Purchase p, String comment){
        id = UUID.randomUUID();
        this.purchase = p;
        this.comment = comment;
    }
}
