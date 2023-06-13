package com.donks.productsservice.Repository;

import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    Optional<Stock> findByProduct(Product product);
}
