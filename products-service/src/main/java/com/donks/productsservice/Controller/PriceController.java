package com.donks.productsservice.Controller;

import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    public ResponseEntity<List<Price>> findAll(){
        return ResponseEntity.ok(priceService.findAll());
    }
}
