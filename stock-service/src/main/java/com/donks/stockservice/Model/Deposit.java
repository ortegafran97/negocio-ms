package com.donks.stockservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposit {

    @Id
    @Column(name="id_deposit",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id_deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller")
    private Seller seller;

    @OneToMany(targetEntity = DepositProduct.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DepositProduct> products;

    private LocalDateTime date = LocalDateTime.now();



}
