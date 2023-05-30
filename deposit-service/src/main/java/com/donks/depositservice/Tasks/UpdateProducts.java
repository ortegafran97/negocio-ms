package com.donks.depositservice.Tasks;

import com.donks.depositservice.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateProducts {

    @Autowired
    ProductsService productsService;

    @Scheduled(fixedRate = 15000) // Se ejecuta cada 1 hr (?
    public void updateProductsTask() {
        productsService.updateProductsData();
    }
}
