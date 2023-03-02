package com.donks.stockservice.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDTO {
    private UUID id;
    private List<DepositProductDTO> products;
    private SellerDTO seller;
    private LocalDateTime date;

}
