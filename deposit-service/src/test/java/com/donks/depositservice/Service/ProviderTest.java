package com.donks.depositservice.Service;

import com.donks.depositservice.Model.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderTest {

    @Autowired
    ProviderService providerService;

    Provider p;
    Optional<Provider> saved;

    @BeforeEach
    public void set_up(){
        // Limpio los registros de los tests anteriores
        providerService
                .findAll()
                .forEach(providerService::delete);

        p = new Provider("Nuevo Proveedor");
        saved = providerService.save(p);
    }

    @Test
    public void add_provider(){
        if(saved.isEmpty())
            fail();
        assertEquals(p,saved.get());
    }

    @Test
    public void delete_provider(){
        providerService.delete(p.getId());
        assertTrue(providerService.findById(p.getId()).isEmpty());
    }

    @Test
    public void find_by_name(){
        Optional<Provider> found = providerService.findByName("Nuevo Proveedor");
        assertEquals(saved,found);
    }

    @Test
    public void find_by_containing_text(){
        providerService.save(new Provider("otro Proveedor"));

        List<Provider> list = providerService.findByText("proveedor");

        assertEquals(2, list.size());
    }
}
