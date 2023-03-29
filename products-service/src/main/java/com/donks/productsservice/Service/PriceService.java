package com.donks.productsservice.Service;

import com.donks.productsservice.Model.Enum.PriceType;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public List<Price> findAll(){
        return priceRepository.findAll();
    }

    public Price save(Price price){
        return priceRepository.save(price);
    }

    public List<Price> findPricesForProduct(Product product){
        return priceRepository.findByProduct(product);
    }

    public List<Price> findPurchasesForProduct(Product p){
        return priceRepository.findByProduct(p)
                .stream()
                .filter(e -> e.getType().equals(PriceType.PURCHASE))
                .collect(Collectors.toList());
    }
    public List<Price> findSalesForProduct(Product p){
        return priceRepository.findByProduct(p)
                .stream()
                .filter(e -> e.getType().equals(PriceType.SALE))
                .collect(Collectors.toList());
    }

}
