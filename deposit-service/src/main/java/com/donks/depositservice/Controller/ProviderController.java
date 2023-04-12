package com.donks.depositservice.Controller;

import com.donks.depositservice.Exceptions.Classes.NotFoundException;
import com.donks.depositservice.Exceptions.Classes.ValidationException;
import com.donks.depositservice.Model.Provider;
import com.donks.depositservice.Service.ProviderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @PostMapping
    public ResponseEntity<Optional<Provider>> save(@RequestBody Provider provider){
        Optional<Provider> created = providerService.save(provider);

        if(created.isEmpty())
            throw new ValidationException("Provider no valido");

        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Provider>> findAll(){
        return ResponseEntity.ok(providerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Provider>> findById(@PathVariable("id") UUID id){
        Optional<Provider> p = providerService.findById(id);

        if(p.isEmpty())
            throw new NotFoundException("El provider no existe");

        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Provider>> update(@PathVariable("id") UUID id,@RequestBody @NotNull Provider provider){
        provider.setId(id);
        Optional<Provider> updated = providerService.update(provider);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(providerService.delete(id));
    }

}
