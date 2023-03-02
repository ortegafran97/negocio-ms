package com.donks.stockservice.Controller;

import com.donks.stockservice.Exceptions.Classes.AlreadyExistsException;
import com.donks.stockservice.Exceptions.Classes.CouldntCompleteException;
import com.donks.stockservice.Exceptions.Classes.NotFoundException;
import com.donks.stockservice.Model.Dtos.ProductDTO;
import com.donks.stockservice.Model.Product;
import com.donks.stockservice.Service.ProductService;
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
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDTO>> findOne(@PathVariable("id") UUID idProduct){
        Optional<ProductDTO> p = productService.findOne(idProduct);

        if(p.isEmpty())
            throw new NotFoundException("No existe el producto.");

        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Optional<ProductDTO>> addProduct (@RequestBody ProductDTO product){
        Optional<ProductDTO> p = productService.addOne(product);

        if(p.isEmpty())
            throw new AlreadyExistsException(String.format("The product with ID="+product.getId()+ " already exists"));

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.ok(productService.deleteOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ProductDTO>> updateProduct(@PathVariable("id") UUID idProduct,@RequestBody ProductDTO product){
        product.setId(idProduct);
        Optional<ProductDTO> p = productService.updateOne(product);
        if(p.isEmpty())
            throw new CouldntCompleteException("No se actualizo el producto");

        return ResponseEntity.ok(p);
    }
}
