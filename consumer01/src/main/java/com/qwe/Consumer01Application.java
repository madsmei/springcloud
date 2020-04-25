package com.qwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/*****
 * Feign的方式实现负载均衡
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Consumer01Application {
    public static void main(String[] args) {
        SpringApplication.run(Consumer01Application.class, args);
    }

}
