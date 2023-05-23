package com.donks.depositservice.Controller;

import com.donks.depositservice.Model.Deposit;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Service.DepositService;
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

    @Autowired
    private DepositService depositService;
    @PostMapping
    public ResponseEntity<Purchase> saveOne(@RequestBody Purchase p){
        Purchase created = purchaseService.saveOne(p);

        //For every purchase a deposit is created
        Deposit d = depositService.saveOne(new Deposit(created,null));

        return ResponseEntity.ok(created);
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
