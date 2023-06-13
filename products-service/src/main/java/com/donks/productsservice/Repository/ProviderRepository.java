package com.donks.productsservice.Repository;

import com.donks.productsservice.Model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    Optional<Provider> findByNameContainingIgnoreCase(String text);
    Optional<Provider> findByName(String name);
}
