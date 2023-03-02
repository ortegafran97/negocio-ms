package com.donks.stockservice;

import com.donks.stockservice.Model.Dtos.DepositDTO;
import com.donks.stockservice.Model.Dtos.DepositProductDTO;
import com.donks.stockservice.Model.Dtos.ProductDTO;
import com.donks.stockservice.Model.Dtos.SellerDTO;
import com.donks.stockservice.Model.Mappers.DepositoMapper;
import com.donks.stockservice.Model.Mappers.ProductMapper;
import com.donks.stockservice.Service.DepositService;
import com.donks.stockservice.Service.ProductService;
import com.donks.stockservice.Service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DepositShould {

    @Autowired
    DepositService depositService;
    @Autowired
    SellerService sellerService;
    @Autowired
    ProductService productService;


    List<DepositDTO> deposits;

    @BeforeEach
    public void setUp(){


        UUID idSeller = UUID.randomUUID();

        sellerService.saveOne(
                new SellerDTO(idSeller,
                        "Franco Ortega",
                        null,
                        null,
                        "0303456",
                        "sj",
                        "arg",
                        "extraData"
                        )
        );

        SellerDTO s1 = sellerService.findOne(idSeller).get();

        List<UUID> productsIds = new ArrayList<UUID>();
        productsIds.add(UUID.randomUUID());
        productsIds.add(UUID.randomUUID());
        productsIds.add(UUID.randomUUID());

        productService.addOne(new ProductDTO(productsIds.get(0),"Producto 1","Descripcion 1","Extra data 1"));
        productService.addOne(new ProductDTO(productsIds.get(1),"Producto 2","Descripcion 2","Extra data 2"));
        productService.addOne(new ProductDTO(productsIds.get(2),"Producto 2","Descripcion 2","Extra data 3"));

        DepositProductDTO p = new DepositProductDTO(
                UUID.randomUUID(),
                productService.findOne(productsIds.get(0)).get(),
                10);

        DepositDTO d = new DepositDTO();
        d.setId(UUID.randomUUID());
        d.setProducts(List.of(p));
        d.setSeller(s1);
        d.setDate(LocalDateTime.now());


        depositService.saveOne(DepositoMapper.dtoToEntity(d));

    }

    @Test
    public void list_all_deposits(){
        assertTrue(true);
    }
}
