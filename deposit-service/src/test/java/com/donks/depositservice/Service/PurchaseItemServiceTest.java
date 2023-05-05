package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Model.PurchaseItem;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
@RunWith(SpringRunner.class)
class PurchaseItemServiceTest {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PurchaseItemService purchaseItemService;

    @Autowired
    ProductsService productsService;

    private Purchase purchase;

    private Product product;

    private List<PurchaseItem> list;

    @BeforeEach
    public void setUp(){
        purchase = new Purchase();
        purchaseService.save(purchase);

        product = new Product("TEST PURCHASE ITEM","Description test");
        product = productsService.save(product);

        list = List.of(
                new PurchaseItem(15.0,2,product)
        );

    }

    @Test
    public void save_new_items(){
        int beforeSave = purchaseItemService.findAll().size();
        list.forEach(purchaseItemService::save);
        int afterSave = purchaseItemService.findAll().size();

        assertTrue(beforeSave < afterSave);
    }

    @Test
    public void delete_item(){
        PurchaseItem old = purchaseItemService.findAll().get(0);
        purchaseItemService.delete(old.getId());
        assertTrue(purchaseItemService.findById(old.getId()).isEmpty());
    }
}