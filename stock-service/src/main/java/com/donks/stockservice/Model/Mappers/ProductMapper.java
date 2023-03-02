package com.donks.stockservice.Model.Mappers;

import com.donks.stockservice.Model.Dtos.ProductDTO;
import com.donks.stockservice.Model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductMapper {

    static public ProductDTO entityToDto(Product entity){
        return Optional.ofNullable(entity)
                .map(e -> new ProductDTO(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getExtraData()
                ))
                .orElse(new ProductDTO());
    }

    static public Product dtoToEntity(ProductDTO dto){
        Product entity = new Product();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setExtraData(dto.getExtraData());

        return entity;
    }
}
