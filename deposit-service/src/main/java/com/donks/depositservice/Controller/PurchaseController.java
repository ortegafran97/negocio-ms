package com.donks.depositservice.Controller;

import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll(){
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Purchase>> findById(UUID id){
        return ResponseEntity.ok(purchaseService.findById(id));
    }


    public ResponseEntity<Optional<Purchase>> saveOne(Purchase p){
        return ResponseEntity.ok(Optional.empty());
    }

}
