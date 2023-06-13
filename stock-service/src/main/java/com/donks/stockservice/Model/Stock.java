package com.donks.stockservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long quantity=0;
    private String location = "store";

    @OneToOne
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return quantity == stock.quantity
                && Objects.equals(id, stock.id)
                && Objects.equals(location, stock.location)
                && Objects.equals(product, stock.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product,location);
    }
}
