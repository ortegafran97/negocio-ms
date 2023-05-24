package com.donks.depositservice.Controller;

import com.donks.depositservice.Model.Deposit;
import com.donks.depositservice.Service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;

    @GetMapping
    public ResponseEntity<List<Deposit>> findAll(){
        return ResponseEntity.ok(depositService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Deposit>> findById(@PathVariable("id") UUID idDeposit){
        return ResponseEntity.ok(depositService.findById(idDeposit));
    }

    @PostMapping
    public ResponseEntity<Deposit> saveOne(@RequestBody Deposit deposit){
        return ResponseEntity.ok(depositService.saveOne(deposit));
    }


}
