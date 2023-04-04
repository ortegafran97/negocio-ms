package com.donks.productsservice.Service;

import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<Product> save(Product product){
        if(product.getId() != null && productRepository.findById(product.getId()).isPresent())
            return Optional.empty();

        LocalDateTime date = LocalDateTime.now();

        if(product.getCreatedAt() == null)
            product.setCreatedAt(date);

        if(product.getUpdatedAt() ==null)
            product.setUpdatedAt(date);

        return Optional.of(productRepository.save(product));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id){
        return productRepository.findById(id);
    }

    public Boolean delete(UUID idProduct){
        productRepository.deleteById(idProduct);

        return productRepository.findById(idProduct).isEmpty();
    }

    public Optional<Product> update(Product product){
        product.setUpdatedAt(LocalDateTime.now());

        return Optional.of(productRepository.save(product));
    }

}
