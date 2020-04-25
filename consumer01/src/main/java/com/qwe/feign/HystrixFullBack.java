package com.qwe.feign;

/**
 * @Description:Hystrix底层使用AOP的解决方案
 * @Date 2020/2/23
 * @Version V1.0
 * @Author Mads
 **/
public class HystrixFullBack implements SchedualServiceHi_FeignCustom {
    @Override
    public String sayHiHollo(String id) {
        //服务熔断了
        System.out.println("服务器进入熔断模式");
        return "";
    }

}
