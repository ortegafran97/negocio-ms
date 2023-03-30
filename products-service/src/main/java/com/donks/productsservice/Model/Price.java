package com.donks.productsservice.Model;

import com.donks.productsservice.Model.Enum.PriceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.enums.Enum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {

    @Id
    @Column(name = "id_price",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private Double price;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private PriceType type;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_product",name = "product")
    private Product product;
}