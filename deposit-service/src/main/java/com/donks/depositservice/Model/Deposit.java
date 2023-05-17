package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private DepositState state;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String comment;

    private Purchase purchase;
}