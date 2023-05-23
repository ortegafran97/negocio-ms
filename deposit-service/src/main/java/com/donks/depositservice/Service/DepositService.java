package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Deposit;
import com.donks.depositservice.Model.DepositState;
import com.donks.depositservice.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    public Deposit saveOne(Deposit deposit){
        return depositRepository.save(deposit);
    }

    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    public Deposit updateState(UUID idDeposito, DepositState state){
        switch (state){
            case PENDING:
                System.out.println("PENDING");
                break;

            case CANCELLED:
                System.out.println("CANCELLED");
                break;

            case RECEIVED:
                System.out.println("RECEIVED");
                break;

            case RETURNED:
                System.out.println("RETURNED");
                break;

            case ACCEPTED:
                System.out.println("ACCEPTED");

                /*
                * Aquí debemos actualizar el stock de los productos que están dentro de
                * la compra
                */

                break;

            default:
                System.out.println("Salida por defecto");
        }

        return new Deposit();
    }


}
