package com.qwe.config;

import feign.Client;
import feign.Contract;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/******
 * Feign 的自动配置
 */
@Configuration
public class FeignCustomConfig {

    /*****
     * 自定义契约，去替换默认的SpringMVC的契约
     * @return
     */
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

    /******
     * 重写 Feign的重试机制
     * @return
     */
    @Bean
    public Retryer feignReryer() {
        /*****
         * 每200毫秒 重试一次，一共试3次
         */
        return new Retryer.Default(200, SECONDS.toMillis(1),3);
    }

    // 空格后跟着注释
//    @Bean
//    public Client.Default feiClient(){
//        return new Client.Default(OkHttp3ClientHttpRequestFactory,)
//    }
}
