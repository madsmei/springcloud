//package com.ribbonconfig;
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//import com.netflix.loadbalancer.RoundRobinRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///*******
// * 自定义的 Ribbon配置
// *
// * 默认采用 轮训的方式
// */
//@Configuration
//public class CustomRibbonConfig {
//
//    /*******
//     * 随即负载策略
//     * @return
//     */
//    @Bean
//    public IRule ribbonRule(){
//        return new RandomRule();
//    }
//
//    /*******
//     * 轮训的负载策略  默认
//     * @return
//     */
//    /*@Bean
//    public IRule ribbonRule(){
//        return new RoundRobinRule();
//    }*/
//
//}
