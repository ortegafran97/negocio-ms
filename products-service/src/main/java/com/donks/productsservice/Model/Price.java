package com.donks.productsservice.Model;

import com.donks.productsservice.Model.Enum.PriceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.enums.Enum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {

    @Id
    @Column(name = "id_price",columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(
//            name = "uuid",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private UUID id;

    private Double price;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private PriceType type = PriceType.SALE;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_product",name = "product")
    private Product product;

    public Price(Product product, Double price, PriceType type){
        //this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

        this.product = product;
        this.type = type;
        this.price = price;
    }

    public Price(Product product, Double price){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.type = PriceType.SALE;

        this.product = product;
        this.price = price;
    }


}
