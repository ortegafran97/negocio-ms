package com.donks.depositservice.Repository;

import com.donks.depositservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Product, UUID> {
}
