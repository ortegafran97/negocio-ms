package com.donks.stockservice.Controller;

import com.donks.stockservice.Model.Deposit;
import com.donks.stockservice.Model.Dtos.DepositDTO;
import com.donks.stockservice.Service.DepositService;
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
    public ResponseEntity<List<DepositDTO>> findAll(){
        return ResponseEntity.ok(depositService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepositDTO>> findOne(UUID idDeposit){
        return ResponseEntity.ok(depositService.findOne(idDeposit));
    }

    @PostMapping
    public ResponseEntity<Optional<DepositDTO>> addOne(@RequestBody Deposit deposit){
        return ResponseEntity.ok(depositService.saveOne(deposit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable("id") UUID depositId){
        return ResponseEntity.ok(depositService.deleteOne(depositId));
    }
}
