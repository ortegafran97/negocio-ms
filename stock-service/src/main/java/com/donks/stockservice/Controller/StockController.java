package com.donks.stockservice.Controller;

import com.donks.stockservice.Exceptions.Classes.NotFoundException;
import com.donks.stockservice.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<HashMap> getProductStock(@PathVariable("id") UUID idProduct){
        Integer stock = stockService.getProductStock(idProduct);

        if(stock == null)
            throw new NotFoundException("Producto no encontrado");


        HashMap<String,String> response= new HashMap<String, String>();

        response.put("id",idProduct.toString());
        response.put("stock",stock.toString());

        return ResponseEntity.ok(response);
    }
}
