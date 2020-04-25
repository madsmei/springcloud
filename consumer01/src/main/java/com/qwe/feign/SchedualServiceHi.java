package com.qwe.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/***
 * 前提：本 接口使用的是SpringMVC提供的契约来实现的
 *
 * Feign 是SpringCloud整合了Eureka，SpringMVC,Ribbon 的HTttp的客户端负载均衡工具，可以直接使用SpringMVC的各种注解（默认），
 * 开启微服务的Feign的客户端。每一个不同的微服务设置一个单独的类似本接口的方式来申明。
 *  Feign会自动 根据方法名自动请求微服务地址。省略了我们自己去拼装URL时参数过多时拼接的复杂度，
 *
 * 此接口的方法，为了快速开发 可以直接把对方微服务的Controller里的方法拷贝过来加以稍微改造即可
 *
 * 注意：
 *      1.Feign中 ，路径参数 必输使用@PathVariable,如果是问号传参（URL方式） 需要使用@RequestParam
 *      2.Feign默认是使用Eureka和Ribbon来访问的。如果想脱离Eureka来说会用。
 *          1）. ribbon.eureka.enabled=false
 *              micoroservice-user-ribbon.listOfServers=127.0.0.1:9001,127.0.0.1:9002
 * feign接口
*/
//@FeignClient(value = "hello-service")
//指定特定的URL
@FeignClient(value = "hello-service",url = "http://localhost:9001/")
public interface SchedualServiceHi {
    @RequestMapping(value = "/hello/hello", method = RequestMethod.GET)
    String sayHiFromClientOne();

    /******
     * 带参数的请求。 Feign 会自动封装并进行请求。(注意这个方法没有对应的微服务接口。只是为了练习带参数的方式)
     * @param id
     * @return
     */
    @RequestMapping(value = "/hello/hello1", method = RequestMethod.GET)
    String sayHiHollo(@RequestParam("id") String id);

    /******
     *发送复杂类型的请求参数
     */
    @PostMapping("/hello/testPost")
    public Object testPostUser(@RequestBody  Object yyuser);

    /******
     * 注意  Feign在发送复杂参数时  即使指定了请求方式是 GET，但是会默认使用POST请求。所以这里一定要注意
     * 如果非要使用GET，则可以使用下面放的来代替此方法(核心思想就是 吧复杂对象类型，拆开分开写)
     * @param yyuser
     * @return
     */
    @RequestMapping(value = "/hello/testGet",method = RequestMethod.GET)
    public Object testGetUser(Object yyuser);
    @RequestMapping(value = "/hello/testGet",method = RequestMethod.GET)
    public Object testGetUser(@RequestParam("id") String id,
                              @RequestParam("name") String name,
                              @RequestParam("adress") String adress);
}
