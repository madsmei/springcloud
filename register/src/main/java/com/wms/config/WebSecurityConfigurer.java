package com.wms.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/******
 * Eureka 服务端 开启了安全验证 以后，client 客户端连接时会一直Cannot execute request on any known server
 * 这个配置文件就是解决此问题
 * https://blog.csdn.net/Lee_SmallNorth/article/details/103730974
 */
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }

}
