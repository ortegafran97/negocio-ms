package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private DepositState state = DepositState.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String comment;

    @OneToOne
    private Purchase purchase = null;

    public Deposit(Purchase p, String comment){
        id = UUID.randomUUID();
        this.purchase = p;
        this.comment = comment;
    }
}
