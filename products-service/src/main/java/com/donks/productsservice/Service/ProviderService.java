package com.donks.productsservice.Service;

import com.donks.productsservice.Model.Provider;
import com.donks.productsservice.Repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public Optional<Provider> saveProvider(Provider provider){
        if(providerRepository.findByName(provider.getName()).isPresent() || providerRepository.findById(provider.getId()).isPresent())
            return Optional.empty();

        return Optional.of(providerRepository.save(provider));
    }

    public List<Provider> findAll(){
        return providerRepository.findAll();
    }

    public Optional<Provider> findById(UUID idProvider){
        return providerRepository.findById(idProvider);
    }

    public Optional<Provider> findByName(String name){
        Optional<Provider> provider = providerRepository.findByName(name);

        if(provider.isPresent())
            return provider;
        return providerRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Provider> updateProvider(UUID id, Provider provider){
        Optional<Provider> exists = providerRepository.findById(id);

        if(exists.isEmpty())
            return Optional.empty();

        provider.setId(id);
        return Optional.of(providerRepository.save(provider));
    }

    public void deleteById(UUID idProvider){
        providerRepository.deleteById(idProvider);
    }

}
