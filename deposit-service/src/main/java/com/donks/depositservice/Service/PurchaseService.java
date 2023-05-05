package com.donks.depositservice.Service;

import com.donks.depositservice.FeignClients.ProductsFeignClient;
import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Repository.PurchaseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductsFeignClient productsFeignClient;


    public Optional<Purchase> save(@NotNull Purchase purchase){
        LocalDateTime date = LocalDateTime.now();
        purchase.setCreatedAt(date);
        purchase.setUpdatedAt(date);

        Purchase newPurchase = purchaseRepository.save(purchase);

        return Optional.of(newPurchase);
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(UUID id){
        return purchaseRepository.findById(id);
    }

    public Boolean delete(UUID id){
        purchaseRepository.deleteById(id);
        return purchaseRepository.findById(id).isEmpty();
    }

}
