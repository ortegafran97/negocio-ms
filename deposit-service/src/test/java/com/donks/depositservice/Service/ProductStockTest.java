package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.ProductStock;
import com.donks.depositservice.Repository.ProductsRepository;
import com.donks.depositservice.Repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductStockTest {

    @Autowired
    StockService stockService;

    @Autowired
    ProductsService productsService;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    StockRepository stockRepository;

    List<Product> productList;
    List<ProductStock> stockList;

    @BeforeEach
    public void set_up(){
        productList = Stream.of(
                new Product("p_1", null),
                new Product("p_2", null),
                new Product("p_3", null),
                new Product("p_4", null)
        )
                .collect(Collectors.toList());

        stockList = productList.stream().map(p -> {
            ProductStock s = new ProductStock();
            s.setProduct(p);
//            return stockService.setStock(p, (long) (Math.random() * 20));
            return s;
        })
                .collect(Collectors.toList());

    }

    @Test
    public void stocks_were_created(){
        List<ProductStock> list = productList
                .stream()
                .map(productsService::save)
                .map(p -> stockService.setStock(p,(long) (Math.random() * 20)))
                .collect(Collectors.toList());


        stockList = stockService.findAll();


        assertTrue(list.size()>0);
    }

    @Test
    public void get_stock_for_a_product(){
        Optional<Product> p = productsService.findByText("p_2");
        if(p.isEmpty())
            fail();

        Optional<ProductStock> stock = stockService.findByProduct(p.get());

        assertTrue(stock.isPresent());
    }

    @Test
    public void update_stock_quantity(){
        Optional<Product> p = productsService.findByText("p_2");
        if(p.isEmpty())
            fail();

        Optional<ProductStock> stock = stockService.findByProduct(p.get());
        if(stock.isEmpty())
            fail();

        ProductStock ps = stock.get();

        ProductStock newStock = stockService.setStock(ps.getProduct(),22);

        assertNotEquals(ps.getQuantity(),newStock.getQuantity());
    }

}
