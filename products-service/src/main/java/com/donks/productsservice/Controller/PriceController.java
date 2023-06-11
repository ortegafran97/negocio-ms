package com.donks.productsservice.Controller;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Exceptions.Classes.ValidationException;
import com.donks.productsservice.Model.Price;
import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Service.PriceService;
import com.donks.productsservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    PriceService priceService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Optional<Price>> save(@RequestBody Price price){
        Optional<Price> p = priceService.save(price);

        if(p.isEmpty())
            throw new ValidationException("No se pudo crear el precio");

        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<Price>> findAll() {
        return ResponseEntity.ok(priceService.findAll());
    }

    @GetMapping("/{idPrice}")
    public ResponseEntity<Optional<Price>> findById(@PathVariable("idPrice") UUID idPrice){
        Optional<Price> price = priceService.findById(idPrice);

        if(price.isEmpty())
            throw new NotFoundException("Price no existe");

        return ResponseEntity.ok(price);
    }

    @GetMapping("/all/{idProduct}")
    public ResponseEntity<List<Price>> findPricesForProduct(@PathVariable("idProduct") UUID idProduct){
        List<Price> list = priceService.findPricesForProduct(idProduct);

        if(list == null)
            throw new NotFoundException("El producto no existe");

        return ResponseEntity.ok(list);
    }

    @GetMapping("/sale/{idProduct}")
    public ResponseEntity<List<Price>> findSalePrices(@PathVariable("idProduct") UUID id){
        List<Price> list = priceService.findSalesForProduct(id);

        if(list == null)
            throw new NotFoundException("El producto no existe");

        return ResponseEntity.ok(list);
    }

    @GetMapping("/purchase/{idProduct}")
    public ResponseEntity<List<Price>> findPurchasePrices(@PathVariable("idProduct") UUID id){
        List<Price> list = priceService.findPurchasesForProduct(id);

        if(list==null)
            throw new NotFoundException("El producto no existe");

        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/{idPrice}")
    public ResponseEntity<Boolean> deletePrice(@PathVariable("idPrice") UUID id){
        Boolean deleted = priceService.deleteById(id);
        if(!deleted)
            throw new NotFoundException("No existe el producto");

        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/last/{idProduct}")
    public ResponseEntity<Price> getLastPriceForProduct(@PathVariable("idProduct") UUID idProduct){
        Optional<Product> p = productService.findById(idProduct);

        if(p.isEmpty())
            throw new NotFoundException("No existe el producto");

        Optional<Price> lastPrice = priceService.findLastSalePriceForProduct(p.get());

        if(lastPrice.isEmpty())
            throw new NotFoundException("No hay precio de venta para el producto");

        return ResponseEntity.ok(lastPrice.get());

    }

}
