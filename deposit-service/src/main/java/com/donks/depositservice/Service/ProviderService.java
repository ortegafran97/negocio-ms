package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Provider;
import com.donks.depositservice.Repository.ProviderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public Optional<Provider> save(@NotNull Provider provider){
        if(provider.getId() != null && findById(provider.getId()).isPresent())
            return Optional.empty();

        return Optional.of(providerRepository.save(provider));
    }

    public Optional<Provider> findById(UUID id){
        return providerRepository.findById(id);
    }

    public List<Provider> findAll(){
        return providerRepository.findAll();
    }

    public Optional<Provider> update(Provider provider){
        Optional<Provider> exists = providerRepository.findById(provider.getId());

        if(exists.isEmpty())
            return Optional.empty();

        return Optional.of(
                providerRepository.save(provider)
        );
    }

    public Boolean delete(UUID id){
        providerRepository.deleteById(id);
        return providerRepository.findById(id).isEmpty();
    }
    public Boolean delete(Provider p){
        providerRepository.delete(p);
        return providerRepository.findById(p.getId()).isEmpty();
    }
}
