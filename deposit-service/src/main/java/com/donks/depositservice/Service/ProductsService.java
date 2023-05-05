package com.donks.depositservice.Service;

import com.donks.depositservice.FeignClients.ProductsFeignClient;
import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    @Autowired
    ProductsFeignClient productsFeignClient;
    @Autowired
    ProductsRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Optional<Product> findById(UUID id){
        return repository.findById(id);
    }

    public Product save(Product p){
        p.setUpdatedAt(LocalDateTime.now());
        return repository.save(p);
    }

    public Optional<Product> update(Product p){
        Optional<Product> exists = findById(p.getId());

        if(exists.isEmpty())
            return Optional.of(save(p));

        p.setUpdatedAt(LocalDateTime.now());
        return Optional.of(repository.save(p));
    }

    public void updateProducts(){
        List<Product> list = productsFeignClient.findAll();
        list.forEach(this::update);
    }
}
