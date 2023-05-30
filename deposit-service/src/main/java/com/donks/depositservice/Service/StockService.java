package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.ProductStock;
import com.donks.depositservice.Model.PurchaseItem;
import com.donks.depositservice.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;

    public void updateStocks(PurchaseItem item){

    }

    public ProductStock setStock(PurchaseItem item){
        return setStock(item.getProduct(),item.getQuantity());
    }

    public ProductStock setStock(Product p, long quantity){
        ProductStock stock = new ProductStock();

        stock.setProduct(p);
        stock.setQuantity(quantity);

        return stockRepository.save(stock);
    }

    public List<ProductStock> findAll(){
        return stockRepository.findAll();
    }

    public Optional<ProductStock> findByProduct(Product product){
        return stockRepository.findByProduct(product).stream().findFirst();
    }

    /*public void delete(UUID id){
        stockRepository.deleteById(id);
    }*/
}
