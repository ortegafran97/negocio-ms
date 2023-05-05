package com.donks.depositservice.FeignClients;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Service.ProductsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductsFeignClientTest {

    @Autowired
    ProductsFeignClient client;

    @Autowired
    ProductsService productsService;


    @Test
    public void update_internal_products(){
        List<Product> old = productsService.findAll();

        System.out.println("~~~~ Old products ~~~~~");
        old.forEach(System.out::println);

        productsService.updateProducts();
        List<Product> updated = productsService.findAll();
        System.out.println("~~~~ updated products ~~~~~");
        updated.forEach(System.out::println);

        assertTrue(old.size()<=updated.size());
    }


}