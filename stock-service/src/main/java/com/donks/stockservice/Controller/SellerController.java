package com.donks.stockservice.Controller;

import com.donks.stockservice.Exceptions.Classes.AlreadyExistsException;
import com.donks.stockservice.Model.Dtos.SellerDTO;
import com.donks.stockservice.Model.Seller;
import com.donks.stockservice.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;


    @GetMapping
    public ResponseEntity<List<SellerDTO>> findAll(){
        return ResponseEntity.ok(sellerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Optional<SellerDTO>> saveOne(@RequestBody SellerDTO seller){
        Optional<SellerDTO> newSeller = sellerService.saveOne(seller);

        if(newSeller.isEmpty())
            throw new AlreadyExistsException("El vendedor ya esta registrado.");

        return ResponseEntity.ok(newSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable("id") UUID idSeller){
        return ResponseEntity.ok(sellerService.deleteOne(idSeller));
    }
}
