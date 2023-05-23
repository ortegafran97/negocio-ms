package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Model.PurchaseItem;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseTest {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    ProductsService productsService;


    @BeforeEach
    public void setUp(){
        List.of(new Product("prod 1",null),
                new Product("prod 2",null),
                new Product("prod 3",null),
                new Product("prod 4",null))
                .forEach(productsService::save);
    }


    @Test
    public void save_purchase_and_items(){
        Purchase p = new Purchase();
        p.setId(UUID.randomUUID());
        List<Product> products = productsService.findAll();
        List<PurchaseItem> list = List.of(
                new PurchaseItem(UUID.randomUUID(), products.get(0),15.0, 1),
                new PurchaseItem(UUID.randomUUID(),products.get(1),14.0, 2),
                new PurchaseItem(UUID.randomUUID(),products.get(2),13.0, 3)
        );
        p.setItems(list);

        purchaseService.saveOne(p);

        assertTrue((long) purchaseService.findAll().size() > 0);
    }

    @Test
    public void find_by_id(){
        Purchase p = new Purchase();
        p.setId(UUID.randomUUID());
        p.setItems(new ArrayList<>());

        Purchase saved = purchaseService.saveOne(p);
        Optional<Purchase> found = purchaseService.findById(saved.getId());

        if(found.isEmpty())
            fail();

        assertEquals(saved, found.get());
    }


    @Disabled
    @Test
    public void delete_purchase(){
        Purchase p = new Purchase();
        p.setId(UUID.randomUUID());
        p.setItems(new ArrayList<>());
        Purchase saved = purchaseService.saveOne(p);

        Optional<Boolean> result = purchaseService.deleteById(saved.getId());

        if(result.isEmpty())
            fail();

        assertTrue(result.get());

    }

}
