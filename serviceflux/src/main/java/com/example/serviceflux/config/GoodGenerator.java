package com.example.serviceflux.config;

import com.example.serviceflux.enety.Good;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GoodGenerator {
    public Flux<Good> findGoods(){
        List<Good> goods = new ArrayList<>();
        goods.add(new Good(1,"小米","2000"));
        goods.add(new Good(2,"华为","4000"));
        goods.add(new Good(3,"苹果","8000"));

        return Flux.fromIterable(goods);
 }
}
