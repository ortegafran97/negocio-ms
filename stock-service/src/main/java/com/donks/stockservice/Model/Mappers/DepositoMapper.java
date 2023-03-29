package com.donks.stockservice.Model.Mappers;

import com.donks.stockservice.Model.Deposit;
import com.donks.stockservice.Model.Dtos.DepositDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DepositoMapper {

    static public DepositDTO entityToDto(Deposit entity){
        return Optional.ofNullable(entity)
                .map(e -> new DepositDTO(
                        e.getId_deposit(),
                        e.getProducts()
                                .stream()
                                .map(DepositProductMapper::entityToDto)
                                .collect(Collectors.toList()),
                        SellerMapper.entityToDTO(e.getSeller()),
                        e.getDate()
                ))
                .orElse(new DepositDTO());
    }

    static public Deposit dtoToEntity(DepositDTO dto){
        Deposit entity = new Deposit();

        entity.setId_deposit(dto.getId());
        entity.setDate(dto.getDate());
        entity.setProducts(dto.getProducts()
                .stream()
                .map(DepositProductMapper::dtoToEntity)
                .collect(Collectors.toList()));
        entity.setSeller(SellerMapper.dtoToEntity(dto.getSeller()));

        return entity;
    }
}
