package com.donks.productsservice.Controller;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> findAll() {
        return ResponseEntity.ok(priceService.findAll());
    }

    @GetMapping("/{idPrice}")
    public ResponseEntity<Optional<Price>> findById(@PathVariable("idPrice") UUID idPrice){
        return ResponseEntity.ok(priceService.findById(idPrice));
    }

    @GetMapping("/all/{idProduct}")
    public ResponseEntity<List<Price>> findPricesForProduct(@PathVariable("idProduct") UUID idProduct)
            throws NotFoundException {
        return ResponseEntity.ok(priceService.findPricesForProduct(idProduct));
    }

    @GetMapping("/sale/{idProduct}")
    public ResponseEntity<List<Price>> findSalePrices(@PathVariable("idProduct") UUID id)
            throws NotFoundException{
        return ResponseEntity.ok(priceService.findSalesForProduct(id));
    }

    @GetMapping("/purchase/{idProduct}")
    public ResponseEntity<List<Price>> findPurchasePrices(@PathVariable("idProduct") UUID id)
            throws NotFoundException{
        return ResponseEntity.ok(priceService.findPurchasesForProduct(id));
    }

    public ResponseEntity<Price> save(@RequestBody Price price){
        return ResponseEntity.ok(priceService.save(price));
    }

}
