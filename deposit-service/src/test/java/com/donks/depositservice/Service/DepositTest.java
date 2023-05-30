package com.donks.depositservice.Service;

import com.donks.depositservice.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositTest {
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProductsService productsService;

    @Autowired
    DepositService depositService;

    Purchase purchase;

    @BeforeEach
    public void setUp(){


        List.of(new Product("prod 1",null),
                        new Product("prod 2",null),
                        new Product("prod 3",null),
                        new Product("prod 4",null))
                .forEach(productsService::save);

        purchase = new Purchase();
        purchase.setId(UUID.randomUUID());
        List<Product> products = productsService.findAll();

        List<PurchaseItem> items = products
                .stream().map(p -> new PurchaseItem(UUID.randomUUID(),
                p,Math.random()*30+1,
                (int) (Math.random()*10)+1))
                .collect(Collectors.toList());

        purchase.setItems(items);
    }

    @Test
    public void deposit_is_created_when_purchase_is_saved(){
        long beforeCount = depositService.findAll().size();
        purchaseService.saveOne(purchase);
        long afterCount = depositService.findAll().size();

        assertTrue(beforeCount < afterCount);
    }

    @Test
    public void update_state_to_accepted(){
        Optional<Deposit> d =  depositService.findByPurchase(
                purchaseService.saveOne(purchase)
        );

        if(d.isEmpty())
            fail();

        Deposit present = d.get();

        depositService.updateState(present.getId(), DepositState.ACCEPTED);


    }

}
