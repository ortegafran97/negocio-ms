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
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @BeforeEach
    public void setUp(){
    }


/*    @Test
    public void save_new_product(){
        Product old = new Product("Product test",null);
        LocalDateTime date = LocalDateTime.of(2023, 4,4,0,0);
        old.setUpdatedAt(date);
        old.setCreatedAt(date);

        Product p = productService.save(old).get();

        Product find = productService.findById(p.getId()).get();

        assertEquals(p,find);
    }

    @Test
    public void delete_product(){
        Optional<Product> p = productService.save(new Product("Product test",null));

       assertTrue(productService.delete(p.get().getId()));
    }

    @Test
    public void update_product(){
        Product old = new Product("Product test",null);
        LocalDateTime date = LocalDateTime.of(2023,04,04,00,00);
        old.setUpdatedAt(date);
        old.setCreatedAt(date);

        Product p = productService.save(old).get();

        p.setDescription("description added");

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        Product updated = productService.update(p).get();

        assertTrue(p.getDescription().equals(updated.getDescription()));

    }

    @Test
    public void save_and_find_prices_for_product(){
        Product p = productService.save(new Product("producto 1",null)).get();

        List.of(
                new Price(p,15.0, PriceType.SALE),
                new Price(p,15.0, PriceType.SALE),
                new Price(p,15.0, PriceType.SALE),
                new Price(p,15.0, PriceType.SALE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE)
        ).forEach(e -> priceService.save(e));

        assertEquals(7,priceService.findPricesForProduct(p).size());
    }

    @Test
    public void find_purchases_for_product() {
        Product p = productService.save(new Product("producto 1",null)).get();

        List.of(
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.SALE)
        ).forEach(e -> priceService.save(e));

        assertEquals(4, priceService.findPurchasesForProduct(p).size());
    }

    @Test
    public void find_sales_for_product() {
        Product p = productService.save(new Product("producto 1",null)).get();

        List.of(
            new Price(p,15.0, PriceType.SALE),
            new Price(p,15.0, PriceType.SALE),
            new Price(p,15.0, PriceType.SALE),
            new Price(p,15.0, PriceType.SALE),
            new Price(p,15.0, PriceType.PURCHASE)
        ).forEach(e -> priceService.save(e));

        assertEquals(4, priceService.findSalesForProduct(p).size());
    }

    @Test
    public void find_prices_sorted(){
        Product p = productService.save(new Product("producto 1",null)).get();

        List.of(
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.PURCHASE),
                new Price(p,15.0, PriceType.SALE)
        ).forEach(e -> {
            try {
                e.setCreatedAt(LocalDateTime.now());
                priceService.save(e);
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Price> list = priceService.findPricesForProduct(p);
        System.out.println(list);

        int i = 0;
        while(i < list.size()-1 && (list.get(i).getCreatedAt().isAfter(list.get(i+1).getCreatedAt()))) i++;

        assertEquals(list.size() - 1,i);

    }

    @Test
    public void get_null_prices_for_non_existent_product(){
        List<Price> list = priceService.findPricesForProduct(UUID.randomUUID());

        assertEquals(null,list);
    }*/

}
