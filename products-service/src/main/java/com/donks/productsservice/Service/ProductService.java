package com.donks.productsservice.Service;

import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id){
        return productRepository.findById(id);
    }

    public Optional<Product> save(Product product){
        return Optional.of(productRepository.save(product));
    }

    public Boolean delete(UUID idProduct){
        productRepository.deleteById(idProduct);

        return productRepository.findById(idProduct).isEmpty();
    }

    public Optional<Product> update(Product product){
        return Optional.of(productRepository.save(product));
    }

}
