package com.donks.productsservice.Service;

import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Model.Stock;
import com.donks.productsservice.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductService productService;


    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Optional<Stock> findByProduct(UUID idProduct){
        Optional<Product> p = productService.findById(idProduct);

        if(p.isEmpty())
            return Optional.empty();

        return stockRepository.findByProduct(p.get());
    }

    public Stock deposit(Stock s){
        Optional<Stock> exists = stockRepository.findByProduct(s.getProduct());

        if(exists.isEmpty())
            return stockRepository.save(s);

        Stock prev = exists.get();

        s.setQuantity(s.getQuantity() + prev.getQuantity());
        return stockRepository.save(s);
    }

    public Stock extract(Stock s){
        Optional<Stock> exists = stockRepository.findByProduct(s.getProduct());

        if(exists.isEmpty() || exists.get().getQuantity()<s.getQuantity()) {
            s.setQuantity(0);
            return stockRepository.save(s);
        }

        Stock old = exists.get();
        s.setQuantity(old.getQuantity()-s.getQuantity());
        return stockRepository.save(s);
    }

    public Stock setStock(Stock s){
        return stockRepository.save(s);
    }
}
