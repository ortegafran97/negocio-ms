package com.donks.depositservice.Service;

import com.donks.depositservice.FeignClients.ProductsFeignClient;
import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Repository.ProductsRepository;
import org.jetbrains.annotations.NotNull;
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
    ProductsRepository productsRepository;

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Optional<Product> findById(UUID id){
        Optional<Product> local = productsRepository.findById(id);

        if(local.isPresent())
            return local;

        Optional<Product> feign = productsFeignClient.findById(id);
        return feign.map(this::save);
    }

    public Product save(@NotNull Product p){
        p.setUpdatedAt(LocalDateTime.now());
        return productsRepository.save(p);
    }

    public Optional<Product> update(@NotNull Product p){
        Optional<Product> exists = findById(p.getId());

        // Si no existe el producto, lo guarda
        if(exists.isEmpty()) {
            return Optional.of(save(p));
        }

        //Si el objeto es distinto del almacenado, guarda el nuevo
        if(!p.equals(exists.get())){
            p.setUpdatedAt(LocalDateTime.now());
            return Optional.of(productsRepository.save(p));
        }

        // Si existe el producto Y es igual, retorna el almacenado localmente
        return exists;
    }

    public void updateProducts(){
        productsFeignClient
                .findAll()
                .stream()
                .map(this::update);
    }
}
