package com.donks.productsservice.Service;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Model.Enum.PriceType;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Optional<Price> setPrice(Price price ){
        Optional<Product> p = productService.findById(price.getProduct().getId());

        if(p.isEmpty())
            return Optional.empty();

        if(price.getPrice().isNaN() || price.getPrice()<0)
            return Optional.empty();

        return Optional.of(priceRepository.save(price));
    }

    public List<Price> findAll(){
        return priceRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
    }

    public Optional<Price> getPriceForProduct(Product product){
        Optional<Price> lastPrice = priceRepository
                .findByProduct(product, Sort.by(Sort.Direction.DESC,"createdAt"))
                .stream()
                .findFirst();

        return lastPrice;
    }

    public List<Price> getHistoricPrices(Product product){
        List<Price> list = new ArrayList<>(priceRepository
                .findByProduct(product, Sort.by(Sort.Direction.DESC, "createdAt")));
        return list;
    }

}
