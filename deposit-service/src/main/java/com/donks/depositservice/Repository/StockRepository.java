package com.donks.depositservice.Repository;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<ProductStock, UUID> {
    Optional<ProductStock> findByProduct(Product product);

    //TODO: agregar metodo para encontrar productos con poco stock
}
