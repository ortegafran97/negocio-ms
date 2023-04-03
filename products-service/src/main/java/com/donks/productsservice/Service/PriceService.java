package com.donks.productsservice.Service;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Model.Enum.PriceType;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    ProductService productService;

    public List<Price> findAll(){
        return priceRepository.findAll();
    }

    public Optional<Price> findById(UUID id){
        return priceRepository.findById(id);
    }

    public Boolean deleteById(UUID id){
        priceRepository.deleteById(id);
        return findById(id).isEmpty();
    }

    public Price save(Price price){
        return priceRepository.save(price);
    }

    public List<Price> findPricesForProduct(Product product){
        return priceRepository.findByProduct(product, Sort.by(Sort.Direction.DESC,"date"));
    }

    public List<Price> findPricesForProduct(UUID idProduct){
        Optional<Product> p = productService.findById(idProduct);

        if(p.isEmpty())
            throw new NotFoundException("Producto no encontrado.");

        return priceRepository.findByProduct(p.get());
    }

    public List<Price> findPurchasesForProduct(Product p){
        return priceRepository
                .findByProduct(p, Sort.by(Sort.Direction.DESC,"date"))
                .stream()
                .filter(e -> e.getType().equals(PriceType.PURCHASE))
                .collect(Collectors.toList());
    }

    public List<Price> findPurchasesForProduct(UUID id){
        Optional<Product> pr = productService.findById(id);
        if(pr.isEmpty())
            return null;

        Product p = pr.get();

        return findPurchasesForProduct(p);
    }

    public List<Price> findSalesForProduct(Product p){
        return priceRepository
                .findByProduct(p, Sort.by(Sort.Direction.DESC,"date"))
                .stream()
                .filter(e -> e.getType().equals(PriceType.SALE))
                .collect(Collectors.toList());
    }

    public List<Price> findSalesForProduct(UUID id){
        Optional<Product> pr = productService.findById(id);
        if(pr.isEmpty())
            return null;

        Product p = pr.get();

        return findSalesForProduct(p);
    }

}
