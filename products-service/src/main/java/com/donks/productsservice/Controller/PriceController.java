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
        return ResponseEntity.ok(priceService.setPrice(price));
    }

    @GetMapping
    public ResponseEntity<List<Price>> findAll() {
        return ResponseEntity.ok(priceService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Price>> getPrice(@PathVariable("id") UUID idProduct){
        Optional<Product> p = productService.findById(idProduct);
        if(p.isEmpty())
            throw new NotFoundException("No existe el producto");

        return ResponseEntity.ok(priceService.getPriceForProduct(p.get()));
    }

    @GetMapping("/historic/{id}")
    public ResponseEntity<List<Price>> getHistoricPrices(@PathVariable("id") UUID idProduct){
        Optional<Product> p = productService.findById(idProduct);
        if(p.isEmpty())
            throw new NotFoundException("No existe el producto");

        return ResponseEntity.ok(priceService.getHistoricPrices(p.get()));
    }



}
