package com.donks.depositservice.Controller;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.ProductStock;
import com.donks.depositservice.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;


    @GetMapping
    public ResponseEntity<List<ProductStock>> findAll(){
        return ResponseEntity.ok(stockService.findAll());
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<ProductStock>> findByProduct(@PathVariable UUID idProduct){
        return ResponseEntity.ok(stockService.findByProduct(idProduct));
    }
}
