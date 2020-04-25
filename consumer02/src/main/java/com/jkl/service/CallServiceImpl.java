package com.jkl.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/****
 * ribbo的方式远程调用
 */
@Service
public class CallServiceImpl implements CallService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Command属性
     * execution.isolation.strategy  执行的隔离策略
     * THREAD 线程池隔离策略  独立线程接收请求
     * SEMAPHORE 信号量隔离策略 在调用线程上执行
     * <p>
     * execution.isolation.thread.timeoutInMilliseconds  设置HystrixCommand执行的超时时间，单位毫秒
     * execution.timeout.enabled  是否启动超时时间，true，false
     * execution.isolation.semaphore.maxConcurrentRequests  隔离策略为信号量的时候，该属性来配置信号量的大小，最大并发达到信号量时，后续请求被拒绝
     * <p>
     * circuitBreaker.enabled   是否开启断路器功能
     * circuitBreaker.requestVolumeThreshold  该属性设置在滚动时间窗口中，断路器的最小请求数。默认20，如果在窗口时间内请求次数19，即使19个全部失败，断路器也不会打开
     * circuitBreaker.sleepWindowInMilliseconds    改属性用来设置当断路器打开之后的休眠时间，休眠时间结束后断路器为半开状态，断路器能接受请求，如果请求失败又重新回到打开状态，如果请求成功又回到关闭状态
     * circuitBreaker.errorThresholdPercentage  该属性设置断路器打开的错误百分比。在滚动时间内，在请求数量超过circuitBreaker.requestVolumeThreshold,如果错误请求数的百分比超过这个比例，断路器就为打开状态
     * circuitBreaker.forceOpen   true表示强制打开断路器，拒绝所有请求
     * circuitBreaker.forceClosed  true表示强制进入关闭状态，接收所有请求
     * <p>
     * metrics.rollingStats.timeInMilliseconds   设置滚动时间窗的长度，单位毫秒。这个时间窗口就是断路器收集信息的持续时间。断路器在收集指标信息的时会根据这个时间窗口把这个窗口拆分成多个桶，每个桶代表一段时间的指标，默认10000
     * metrics.rollingStats.numBuckets   滚动时间窗统计指标信息划分的桶的数量，但是滚动时间必须能够整除这个桶的个数，要不然抛异常
     * <p>
     * requestCache.enabled   是否开启请求缓存，默认为true
     * requestLog.enabled 是否打印日志到HystrixRequestLog中，默认true
     *
     * @HystrixCollapser 请求合并
     * maxRequestsInBatch  设置一次请求合并批处理中允许的最大请求数
     * timerDelayInMilliseconds  设置批处理过程中每个命令延迟时间
     * requestCache.enabled   批处理过程中是否开启请求缓存，默认true
     * <p>
     * threadPoolProperties
     * threadPoolProperties 属性
     * coreSize   执行命令线程池的最大线程数，也就是命令执行的最大并发数，默认10
     */
    @HystrixCommand(fallbackMethod = "hystrixFullBack",
            commandKey = "queryContents",
            groupKey = "querygroup-one",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "100"),//信号量的数量
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),//隔离策略
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000000000")
            },
            //threadPoolKey 指定线程池。如果多个@HystrixCommand都都指定了相同的值，则使用相同的线程池
            threadPoolKey = "queryContentshystrixJackpool", threadPoolProperties = {
//            @HystrixProperty(name = "coreSize", value = "100")//线程池策略下线程的大小
    })
    @Override
    public String sayHello(String name) {
        return restTemplate.getForObject("http://hello-service/hello/hello?name={1}", String.class, name);
    }

    /**
     * 断路由调用方法(服务降级逻辑 写在这里)，对异常的友好封装，
     *      1.返回参数类型，要和 主方法 一致。
     *      2.降级方法 可以再次降级
     * @param name
     * @return
     */
//    @HystrixCommand(threadPoolKey = "queryContentshystrixJackpool")
    public String hystrixFullBack(String name) {
        System.out.println("调用熔断方法 - error >>>>>>>>>>");
        return "hi," + name + ",sorry,error!";
    }
}
