package com.donks.stockservice.Service;

import com.donks.stockservice.Model.Dtos.DepositProductDTO;
import com.donks.stockservice.Model.Mappers.DepositProductMapper;
import com.donks.stockservice.Repository.DepositProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    DepositProductRepository repository;

    public List<DepositProductDTO> findAll(){
        return repository.findAll()
                .stream().map(DepositProductMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Integer getProductStock(UUID idProduct){
        List<DepositProductDTO> list = findAll()
                .stream().filter(p-> p.getProduct().getId().equals(idProduct))
                .toList();

        if(list.isEmpty())
            return null;

        Integer sum = list.stream()
                .map(DepositProductDTO::getQuantity)
                .reduce(0, Integer::sum);
        return sum;
    }
}
