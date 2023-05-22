package com.donks.depositservice.Controller;

import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> saveOne(@RequestBody Purchase p){
        return ResponseEntity.ok(purchaseService.saveOne(p));
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll(){
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Purchase>> findById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(purchaseService.findById(id));
    }

}
