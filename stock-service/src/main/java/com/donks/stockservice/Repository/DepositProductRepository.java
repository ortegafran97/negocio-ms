package com.donks.stockservice.Repository;

import com.donks.stockservice.Model.DepositProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepositProductRepository extends JpaRepository<DepositProduct, UUID> {

}
