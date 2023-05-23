package com.donks.depositservice.Service;

import com.donks.depositservice.Model.*;
import com.donks.depositservice.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProviderService providerService;

    @Autowired
    private DepositService depositService;

    public Purchase saveOne(Purchase p){
        setProduct(p.getItems());
        Optional<Provider> provider;


        if(p.getProvider()!=null) {
            provider = providerService.findById(p.getProvider().getId());

            if (provider.isEmpty())
                p.setProvider(null);
            else p.setProvider(provider.get());
        }
        Purchase compra = purchaseRepository.save(p);

        depositService.saveOne(new Deposit(compra,null));

        return compra;
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(UUID id){
        return purchaseRepository.findById(id);
    }

    public Optional<Boolean> deleteById(UUID id){
        Optional<Purchase> p = purchaseRepository.findById(id);
        if(p.isEmpty())
            return Optional.empty();

        purchaseRepository.deleteById(id);
        return Optional.of(purchaseRepository.findById(id).isEmpty());
    }

    public void setProduct(List<PurchaseItem> items){
        for (PurchaseItem i: items) {
            Optional<Product> p = productsService.findById(i.getProduct().getId());
            p.ifPresent(i::setProduct);
        }
    }


}
