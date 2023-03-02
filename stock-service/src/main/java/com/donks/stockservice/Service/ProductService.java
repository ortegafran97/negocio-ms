package com.donks.stockservice.Service;

import com.donks.stockservice.Model.Dtos.ProductDTO;
import com.donks.stockservice.Model.Mappers.ProductMapper;
import com.donks.stockservice.Model.Product;
import com.donks.stockservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        return productRepository
                .findAll()
                .stream()
                .map(ProductMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> findOne(UUID idProduct) {
        return productRepository
                .findById(idProduct)
                .map(ProductMapper::entityToDto);
    }

    public Optional<ProductDTO> addOne(ProductDTO product){
        if(product.getId() != null && findOne(product.getId()).isPresent())
            return Optional.empty();

        return Optional.of(ProductMapper.entityToDto(productRepository.save(ProductMapper.dtoToEntity(product))));
    }

    public Boolean deleteOne(UUID idProduct){
        productRepository.deleteById(idProduct);
        Optional<ProductDTO> empty = findOne(idProduct);
        return empty.isEmpty();
    }

    public Optional<ProductDTO> updateOne(ProductDTO product){
        Optional<Product> updatable = productRepository.findById(product.getId());

        if(updatable.isEmpty())
            return Optional.empty();

        Product p = ProductMapper.dtoToEntity(product);
        product.setId(p.getId());
        return Optional.of(ProductMapper
                .entityToDto(productRepository.save(
                        ProductMapper.dtoToEntity(product))));
    }

}
