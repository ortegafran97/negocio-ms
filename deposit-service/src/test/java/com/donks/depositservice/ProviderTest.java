package com.donks.depositservice;

import com.donks.depositservice.Model.Provider;
import com.donks.depositservice.Service.ProviderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderTest {

    @Autowired
    ProviderService providerService;

    List<Provider> list;

    @BeforeEach
    public void set_up(){
        list = List.of(
                new Provider("Provider 1"),
                new Provider("Provider 2"),
                new Provider("Provider 3")
        );
        list.forEach(providerService::save);
    }

    @AfterEach
    public void clean_up(){
        providerService.findAll().forEach(providerService::delete);
    }


    @Test
    public void save_new_provider(){
        Provider p = new Provider("Nuevo provider");

        providerService.save(p);

        assertEquals(providerService.findById(p.getId()).get(), p);
    }

    @Test
    public void find_all_providers(){
        assertEquals(3, providerService.findAll().size());
    }

    @Test
    public void find_provider_by_id(){
        Provider p = new Provider("Nuevo provider");

        providerService.save(p);

        assertEquals(p,providerService.findById(p.getId()).get());
    }

    @Test
    public void update_provider(){
        Provider p = new Provider("Otro provider");
        providerService.save(p);

        p.setName("Updated name");
        providerService.update(p);

        assertEquals(p, providerService.findById(p.getId()).get());
    }

    @Test
    public void delete_provider(){
        List<Provider> list = providerService.findAll();
        Provider p = list.get(0);

        providerService.delete(p);

        assertEquals(Optional.empty(),providerService.findById(p.getId()));
    }
}
