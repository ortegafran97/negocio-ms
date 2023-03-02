package com.donks.stockservice.Service;

import com.donks.stockservice.Model.Dtos.SellerDTO;
import com.donks.stockservice.Model.Mappers.SellerMapper;
import com.donks.stockservice.Model.Seller;
import com.donks.stockservice.Repository.SellerRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<SellerDTO> findAll(){
        return sellerRepository.findAll()
                .stream().map(SellerMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SellerDTO> findOne(UUID idSeller) {
        Optional<Seller> response = sellerRepository.findById(idSeller);

        if (response.isEmpty())
            return Optional.empty();

        return Optional.of(SellerMapper.entityToDTO(response.get()));
    }

    public Optional<SellerDTO> saveOne(SellerDTO seller){
        if(findOne(seller.getId()).isPresent())
            return Optional.empty();

        SellerDTO newSeller = SellerMapper.entityToDTO(sellerRepository.save(SellerMapper.dtoToEntity(seller)));

        return Optional.of(newSeller) ;
    }

    public Boolean deleteOne(UUID idSeller){
        sellerRepository.deleteById(idSeller);
        return findOne(idSeller).isEmpty();
    }

    public Optional<SellerDTO> updateOne(SellerDTO seller){
        Optional<SellerDTO> first = findOne(seller.getId());

        if(first.isEmpty())
            return Optional.empty();

        return Optional.of(SellerMapper
                .entityToDTO(sellerRepository.save(SellerMapper.dtoToEntity(seller)))
        );
    }

}
