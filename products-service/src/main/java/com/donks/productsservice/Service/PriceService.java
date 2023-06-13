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
        return priceRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
    }

    public Optional<Price> getPriceForProduct(Product product){
        Optional<Price> lastPrice = priceRepository
                .findByProduct(product, Sort.by(Sort.Direction.DESC,"createdAt"))
                .stream()
                .findFirst();

        return lastPrice;
    }

}
