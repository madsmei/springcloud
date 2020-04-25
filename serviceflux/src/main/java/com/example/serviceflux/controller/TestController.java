package com.example.serviceflux.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("")
public class TestController {


    /**
     * 扩展ReactiveCrudRepository接口，提供基本的CRUD操作
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * spring-boot-starter-data-mongodb-reactive提供的通用模板
     */
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TestController(RestaurantRepository restaurantRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @GetMapping("/reactive/restaurants")
    public Flux<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/reactive/restaurants/{id}")
    public Mono<Restaurant> get(@PathVariable String id) {
        return restaurantRepository.findById(id);
    }

    @PostMapping("/reactive/restaurants")
    public Flux<Restaurant> create(@RequestBody Flux<Restaurant> restaurants) {
        return restaurants
                .buffer(10000)
                .flatMap(rs -> reactiveMongoTemplate.insert(rs, Restaurant.class));
    }

    @DeleteMapping("/reactive/restaurants/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return restaurantRepository.deleteById(id);
    }


}
