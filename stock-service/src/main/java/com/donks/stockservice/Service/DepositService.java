package com.donks.stockservice.Service;

import com.donks.stockservice.Model.Deposit;
import com.donks.stockservice.Model.Dtos.DepositDTO;
import com.donks.stockservice.Model.Mappers.DepositoMapper;
import com.donks.stockservice.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public List<DepositDTO> findAll(){
        return depositRepository.findAll().stream().map(DepositoMapper::entityToDto).collect(Collectors.toList());
    }

    public Optional<DepositDTO> findOne(UUID idDepo){
        return depositRepository.findById(idDepo).map(DepositoMapper::entityToDto);
    }

    public Optional<DepositDTO> saveOne(Deposit deposit){
        return Optional.of(DepositoMapper.entityToDto(depositRepository.save(deposit)));
    }

    public Boolean deleteOne(UUID idDeposit){
        depositRepository.deleteById(idDeposit);
        Optional<Deposit> empty = findOne(idDeposit).map(DepositoMapper::dtoToEntity);
        return empty.isEmpty();
    }

    public List<DepositDTO> getDepositsForProduct(UUID idProduct){
        List<DepositDTO> contiene = findAll()
                .stream().filter(p -> containsProduct(p,idProduct))
                .toList();

        return contiene;
    }

    public Boolean containsProduct(DepositDTO deposit, UUID idProduct){
        Boolean contiene = deposit.getProducts()
                .stream().anyMatch(p -> p.getProduct().getId().equals(idProduct));

        return contiene;
    }

}
