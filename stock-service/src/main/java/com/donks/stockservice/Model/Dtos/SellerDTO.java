package com.donks.stockservice.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
    private UUID id;
    private String name;
    private String cuil;
    private String cuit;
    private String cellphone;
    private String province;
    private String country;
    private String extraData;

}
