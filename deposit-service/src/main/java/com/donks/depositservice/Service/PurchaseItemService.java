package com.donks.depositservice.Service;

import com.donks.depositservice.FeignClients.ProductsFeignClient;
import com.donks.depositservice.Model.PurchaseItem;
import com.donks.depositservice.Repository.PurchaseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseItemService {

    @Autowired
    PurchaseItemRepository repository;

    public Optional<PurchaseItem> save(PurchaseItem p){
        return Optional.of(repository.save(p));
    }

    public List<PurchaseItem> findAll(){
        return repository.findAll();
    }

    public Optional<PurchaseItem> findById(UUID id){
        return repository.findById(id);
    }

    public Boolean delete(UUID id){
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }



}
