package com.donks.productsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long quantity = 0;
    private String location = "local";

    @OneToOne
    private Product product;

    public Stock(Product p, long quantity, String location){
        this.id = UUID.randomUUID();
        this.product = p;
        this.quantity = quantity;
        this.location = location;
    }

}
