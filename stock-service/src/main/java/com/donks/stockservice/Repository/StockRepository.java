package com.donks.stockservice.Repository;

import com.donks.stockservice.Model.Product;
import com.donks.stockservice.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    List<Stock> findByProduct(Product product);
}
