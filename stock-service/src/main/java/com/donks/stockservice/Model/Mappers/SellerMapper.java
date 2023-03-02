package com.donks.stockservice.Model.Mappers;

import com.donks.stockservice.Model.Dtos.SellerDTO;
import com.donks.stockservice.Model.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerMapper {

    static public SellerDTO entityToDTO(Seller entity){
        return Optional
                .ofNullable(entity)
                .map(e -> new SellerDTO(
                        e.getId(),
                        e.getName(),
                        e.getCuil(),
                        e.getCuit(),
                        e.getCellphone(),
                        e.getProvince(),
                        e.getCountry(),
                        e.getExtraData()
                )).orElse(new SellerDTO());
    }

   static public Seller dtoToEntity(SellerDTO dto){
        Seller entity = new Seller();
        entity.setId(dto.getId());
        entity.setCuit(dto.getCuit());
        entity.setCuil(dto.getCuil());
        entity.setName(dto.getName());
        entity.setCellphone(dto.getCellphone());
        entity.setCountry(dto.getCountry());
        entity.setProvince(dto.getProvince());
        entity.setExtraData(dto.getExtraData());
        return entity;
    }
}
