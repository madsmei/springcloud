package com.qwe.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /*****
     * Ribbo
     * @return
     */
    @Bean
    @LoadBalanced//这个注解 一定要加
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
