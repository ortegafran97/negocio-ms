package com.donks.stockservice.Model.Dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositProductDTO {

    private UUID id;
    private ProductDTO product;
    private Integer quantity;
}
