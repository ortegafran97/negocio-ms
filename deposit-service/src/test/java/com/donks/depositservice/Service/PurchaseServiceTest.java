package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Model.PurchaseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PurchaseServiceTest {

    @Autowired
    PurchaseService purchaseService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void save_new_purchase(){
        Purchase p = new Purchase();
        p.setId(UUID.randomUUID());
        Optional<Purchase> created = purchaseService.save(p);

        if(created.isEmpty())
            fail();

        assertEquals(p, created.get());
    }

}