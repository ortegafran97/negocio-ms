package com.donks.depositservice.FeignClients;

import com.donks.depositservice.Model.Product;
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


    @Test
    public void find_all_products(){
        List<Product> list = client.findAll();

        list.forEach(System.out::println);

        assertTrue(list.size()>0);

    }
}