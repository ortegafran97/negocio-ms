package com.donks.productsservice.Controller;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Exceptions.Classes.ValidationException;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Service.PriceService;
import com.donks.productsservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Optional<Price>> save(@RequestBody Price price){
        //TODO: save price
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<Price>> findAll() {
        //TODO:
        return ResponseEntity.ok(priceService.findAll());
    }

    public ResponseEntity<Optional<Price>> getPrice(Product product){
        return ResponseEntity.ok(priceService.getPriceForProduct(product));
    }



}
