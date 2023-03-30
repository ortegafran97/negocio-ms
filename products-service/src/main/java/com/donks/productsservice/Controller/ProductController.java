package com.donks.productsservice.Controller;

import com.donks.productsservice.Model.Product;
import com.donks.productsservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productsService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findOne(@PathVariable("id") UUID idProduct){
        return ResponseEntity.ok(productsService.findById(idProduct));
    }

    @PostMapping
    public ResponseEntity<Optional<Product>> save(@RequestBody Product product){
        return ResponseEntity.ok(productsService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> update(@PathVariable("id") UUID id,@RequestBody Product product){
        product.setId(id);
        return ResponseEntity.ok(productsService.update(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(UUID id){
        return ResponseEntity.ok(productsService.delete(id));
    }

}
