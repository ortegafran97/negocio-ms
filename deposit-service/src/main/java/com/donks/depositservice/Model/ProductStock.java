package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductStock {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long quantity=0;

    @OneToOne
    private Product product;

}
