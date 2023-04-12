package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "id_product",columnDefinition = "uuid")
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Product(String name, String description){
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;

        LocalDateTime d = LocalDateTime.now();
        this.createdAt = d;
        this.updatedAt = d;
    }

}
