package com.donks.depositservice.Repository;

import com.donks.depositservice.Model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    Optional<Provider> findByName(String name);
    List<Provider> findByNameContainingIgnoreCase(String name);

}
