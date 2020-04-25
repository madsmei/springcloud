package com.jkl;

import com.jkl.config.CustomRibbonConfig;
import com.jkl.config.ExcludeRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;


@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard//HysTrix的数据的图形化工具   http://localhost:9090/hystrix   填入 http://localhost:9090/actuator.stream查看数据
//使用的自己自定义的配置文件  name 是访问的目标地址的Eureka的微服务的名字
//这样的好处是 如果 此项目中访问了多个微服务，我们就可以单独配置不同的负载策略
@RibbonClient(name = "hello-service",configuration = CustomRibbonConfig.class)
@ComponentScan(excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = ExcludeRibbonConfig.class)})
public class Consumer02Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer02Application.class, args);
    }

}
