package com.donks.stockservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @Column(name="id_seller",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String cuil;
    private String cuit;
    private String cellphone;
    private String province;
    private String country;
    private String extraData;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "id_deposit",
            cascade = CascadeType.ALL,
            targetEntity = Deposit.class
    )
    private List<Deposit> deposits;
}
