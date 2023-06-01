package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Deposit;
import com.donks.depositservice.Model.DepositState;
import com.donks.depositservice.Model.Product;
import com.donks.depositservice.Model.Purchase;
import com.donks.depositservice.Repository.DepositRepository;
import com.netflix.discovery.converters.Auto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Autowired
    ProductsService productsService;

    @Autowired
    StockService stockService;

    public Deposit saveOne(Deposit deposit){
        return depositRepository.save(deposit);
    }

    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    public Optional<Deposit> findById(UUID id){
        return depositRepository.findById(id);
    }

    public Optional<Deposit> findByPurchase(Purchase p){
        return depositRepository.findByPurchase(p);
    }

    public Optional<Deposit> updateState(UUID idDeposito, @NotNull DepositState state){

        Optional<Deposit> exists = depositRepository.findById(idDeposito);
        if(exists.isEmpty())
            return Optional.empty();

        Deposit d = exists.get();

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
                d.setState(state);
                depositRepository.save(d);
                break;

            case ACCEPTED:
                System.out.println("ACCEPTED");

                /*
                * Aquí actualizamos el stock de los productos
                * que están dentro de la compra
                */

                Purchase p = d.getPurchase();

                p.getItems()
                        .forEach( i -> {
                            System.out.println(i);
                            //TODO: actualizar ProductStock

                            stockService.setStock(i);

                        });
                break;

            default:
                System.out.println("Salida por defecto");
        }

        return Optional.empty();
    }


}
