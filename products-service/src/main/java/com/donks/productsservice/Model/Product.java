package com.donks.productsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "id_product",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String code;


    public Product(String name, String description){
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

}
