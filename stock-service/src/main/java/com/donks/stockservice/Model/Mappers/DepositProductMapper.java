package com.donks.stockservice.Model.Mappers;

import com.donks.stockservice.Model.DepositProduct;
import com.donks.stockservice.Model.Dtos.DepositProductDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepositProductMapper {

    static public DepositProductDTO entityToDto(DepositProduct entity){
        return Optional.ofNullable(entity)
                .map(e -> new DepositProductDTO(
                        entity.getId(),
                        ProductMapper.entityToDto(entity.getProduct()),
                        entity.getQuantity()
                ))
                .orElse(new DepositProductDTO());
    }

    static public DepositProduct dtoToEntity(DepositProductDTO dto){
        DepositProduct entity = new DepositProduct();

        entity.setProduct(ProductMapper.dtoToEntity(dto.getProduct()));
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());

        return entity;
    }
}
