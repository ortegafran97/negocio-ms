package com.donks.productsservice;

import com.donks.productsservice.Model.Enum.PriceType;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Service.PriceService;
import com.donks.productsservice.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsShould {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @BeforeEach
    public void setUp(){
    }


    @Test
    public void save_new_product(){
        Optional<Product> p = productService.save(new Product("Product test",null));

        Optional<Product> find = productService.findById(p.get().getId());

        assertEquals(p,find);
    }

    @Test
    public void delete_product(){
        Optional<Product> p = productService.save(new Product("Product test",null));

       assertTrue(productService.delete(p.get().getId()));
    }

    @Test
    public void update_product(){
        Product p = productService.save(new Product("Product test",null)).get();

        p.setDescription("description added");
        productService.update(p);

        assertEquals(p,productService.findById(p.getId()).get());
    }

    @Test
    public void find_purchases_for_product() {
        Product p = productService.save(new Product(UUID.randomUUID(),"producto 1",null)).get();

        List.of(
            new Price(UUID.randomUUID(),15.0, LocalDateTime.now(), PriceType.PURCHASE, p),
            new Price(UUID.randomUUID(),12.0, LocalDateTime.now(), PriceType.PURCHASE, p),
            new Price(UUID.randomUUID(),13.0, LocalDateTime.now(), PriceType.PURCHASE, p),
            new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.PURCHASE, p),
            new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.SALE, p)
        ).forEach(e -> priceService.save(e));

        assertEquals(4, priceService.findPurchasesForProduct(p).size());
    }

    @Test
    public void find_sales_for_product() {
        Product p = productService.save(new Product(UUID.randomUUID(),"producto 1",null)).get();

        List.of(
            new Price(UUID.randomUUID(),15.0, LocalDateTime.now(), PriceType.SALE, p),
            new Price(UUID.randomUUID(),12.0, LocalDateTime.now(), PriceType.SALE, p),
            new Price(UUID.randomUUID(),13.0, LocalDateTime.now(), PriceType.SALE, p),
            new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.SALE, p),
            new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.PURCHASE, p)
        ).forEach(e -> priceService.save(e));

        assertEquals(4, priceService.findSalesForProduct(p).size());
    }



}