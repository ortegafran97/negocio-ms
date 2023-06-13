package com.donks.productsservice.Repository;

import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
    List<Price> findAll();
    List<Price> findAll(Sort sort);

    List<Price> findByProduct(Product product);
    List<Price> findByProduct(Product product,Sort sort);



}
