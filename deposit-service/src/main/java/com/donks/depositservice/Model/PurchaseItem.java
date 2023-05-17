package com.donks.depositservice.Model;

import com.donks.depositservice.Service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name="purchase_item")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItem {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="FK_Item_Prod", insertable = true)
    private Product product;

    private Double price;
    private Integer quantity;
}
