package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductStock {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private long quantity = 0;

    @OneToOne
    @NotNull
    private Product product;



}
