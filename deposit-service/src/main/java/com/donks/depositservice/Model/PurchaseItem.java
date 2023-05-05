package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private Double price;
    private Integer quantity;

    @OneToOne
    @JoinColumn(referencedColumnName = "id_product",
            name="product",
            nullable = false,
            insertable = true)
    private Product product;

    public PurchaseItem(Double price, Integer quantity, Product p){
        this.id = UUID.randomUUID();

        this.price = price;
        this.quantity = quantity;
        this.product = p;

    }
}
