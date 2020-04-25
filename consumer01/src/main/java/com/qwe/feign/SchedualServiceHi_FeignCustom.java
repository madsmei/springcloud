package com.qwe.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qwe.config.FeignCustomConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/***
 * 前提：本 接口使用的是Feign自己提供的契约来实现的
 *fallback的方式 不能获取具体异常。不建议使用
 * feign接口
*/
//@FeignClient(value = "hello-service")
//指定特定的URL
@FeignClient(value = "hello-service",
        url = "http://localhost:9001/",
        configuration = FeignCustomConfig.class,
//        fallback = HystrixFullBack.class
        fallbackFactory = StudentServiceFallbackFactory.class
)
public interface SchedualServiceHi_FeignCustom {

    /******
     * 带参数的请求。 Feign 会自动封装并进行请求。(注意这个方法没有对应的微服务接口。只是为了练习带参数的方式)
     * @param id
     * @return
     */
    @RequestLine("GET /hello/hello1?id={id}")
//    @HystrixCommand(fallbackMethod = "hystrixFullBack")
    String sayHiHollo(@Param("id") String id);


}
