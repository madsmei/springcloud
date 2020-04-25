package com.jkl.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*******
 * java 配置的方式
 * 因为Application本身依赖Ribbon的包，所以在自定义的配置文件要放到Applicaition扫描不到的包下，不然会不起效
 * App会扫描本包及其子包，
 * 如果非要把自定义的Ribbon配置文件放到 Application扫描包下，
 * 就要在Application的主文件里的
 * @ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeRibbonConfig.class)})
 * 来让Application 不扫描某些文件
 *
 * 自定义的 Ribbon配置
 *
 * 默认采用 轮训的方式
 */
@Configuration
@ExcludeRibbonConfig
public class CustomRibbonConfig {

    /*******
     * 随即负载策略
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

    /*******
     * 轮训的负载策略  默认
     * @return
     */
    /*@Bean
    public IRule ribbonRule(){
        return new RoundRobinRule();
    }*/

}
