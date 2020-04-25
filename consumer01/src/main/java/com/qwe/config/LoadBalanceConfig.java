package com.qwe.config;

import com.qwe.ribbo.config.RibbonLoadBalanceMicroOrderConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/*
 * 这个是针对 micro-order服务的 ribbon配置,如果我们想对每一个服务有不同的负载策略，就可以按照此方式处理
 * */
@Configuration
@RibbonClients(value = {
        @RibbonClient(name = "micro-order",configuration = RibbonLoadBalanceMicroOrderConfig.class)
})
public class LoadBalanceConfig {
}
