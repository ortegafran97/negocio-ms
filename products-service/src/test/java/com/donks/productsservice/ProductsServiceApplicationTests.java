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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductsServiceApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	PriceService priceService;

	@BeforeEach
	public void setUp(){
	}

	@Test
	public void get_purchases_for_product() {

		Product p = productService.save(new Product(UUID.randomUUID(),"producto 1",null)).get();
		Price price = new Price(UUID.randomUUID(),15.0, LocalDateTime.now(), PriceType.PURCHASE, p);

		List<Price> list = List.of(
				new Price(UUID.randomUUID(),15.0, LocalDateTime.now(), PriceType.PURCHASE, p),
				new Price(UUID.randomUUID(),12.0, LocalDateTime.now(), PriceType.PURCHASE, p),
				new Price(UUID.randomUUID(),13.0, LocalDateTime.now(), PriceType.PURCHASE, p),
				new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.PURCHASE, p)
		);
		list.forEach(e -> priceService.save(e));

		assertEquals(4, priceService.findPurchasesForProduct(p).size());
	}

	@Test
	public void get_sales_for_product() {

		Product p = productService.save(new Product(UUID.randomUUID(),"producto 1",null)).get();

		List<Price> list = List.of(
				new Price(UUID.randomUUID(),15.0, LocalDateTime.now(), PriceType.SALE, p),
				new Price(UUID.randomUUID(),12.0, LocalDateTime.now(), PriceType.SALE, p),
				new Price(UUID.randomUUID(),13.0, LocalDateTime.now(), PriceType.SALE, p),
				new Price(UUID.randomUUID(),1.0, LocalDateTime.now(), PriceType.SALE, p)
		);
		list.forEach(e -> priceService.save(e));

		assertEquals(4, priceService.findSalesForProduct(p).size());
	}



}
