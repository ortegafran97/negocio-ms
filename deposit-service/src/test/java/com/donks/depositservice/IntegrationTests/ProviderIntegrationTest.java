package com.donks.depositservice.IntegrationTests;

import com.donks.depositservice.Model.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ProviderIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void add_provider(){
        Provider p = new Provider("Provider integration");

        ResponseEntity<Provider>  responseEntity = testRestTemplate.postForEntity("/provider", p, Provider.class);

        assertEquals(HttpStatus.resolve(200), responseEntity.getStatusCode());
    }
}
