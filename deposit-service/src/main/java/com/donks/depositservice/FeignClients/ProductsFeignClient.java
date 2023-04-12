package com.donks.depositservice.FeignClients;

import com.donks.depositservice.Model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(name="products-service", path="/product")
public interface ProductsFeignClient {

    @GetMapping
    List<Product> findAll();

    @GetMapping("/{id}")
    Optional<Product> findOne(@PathVariable("id") UUID id);



}
