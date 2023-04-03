package com.donks.productsservice.Controller;

import com.donks.productsservice.Exceptions.Classes.NotFoundException;
import com.donks.productsservice.Exceptions.Classes.ValidationException;
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
        Optional<Product> p = productsService.findById(idProduct);

        if(p.isEmpty())
            throw new NotFoundException("El producto no existe");

        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Optional<Product>> save(@RequestBody Product product){
        Optional<Product> p = productsService.save(product);

        if(p.isEmpty())
            throw new ValidationException("El producto ya existe");

        return ResponseEntity.ok(p);
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
