package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Provider;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Model.PurchaseItem;
import com.donks.depositservice.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProviderService providerService;

    public Purchase saveOne(Purchase p){
        setProduct(p.getItems());

        Optional<Provider> provider = providerService.findById(p.getProvider().getId());

        if(provider.isEmpty())
            p.setProvider(null);

        return purchaseRepository.save(p);
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
            Product p = productsService.findById(i.getProduct().getId()).get();
            i.setProduct(p);
        }
    }


}
