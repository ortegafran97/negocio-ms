package com.donks.stockservice.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){return new RestTemplate();}
}
