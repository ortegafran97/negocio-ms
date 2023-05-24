package com.donks.depositservice.Repository;

import com.donks.depositservice.Model.Deposit;
import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Optional<Deposit> findByPurchase(Purchase p);
}
