package com.poi.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*****
 * @Aspect 注解 生命此类是个切面
 * 将本切面（整个类）交由Spring打理
 *
 */
@Component
@Aspect
public class MadsAOP {

    /*****
     * 声明 切入点
     *
     * execution 声明连接点
     */
    @Pointcut("execution(* com.poi.service..*.*(..))")
    public void poinCut(){

    }

    /******
     * 切入点的通知。这里直接写是切入点的方法名
     * 比如说 开启事务
     */
    @Before("poinCut()")
    public void before(){
        System.out.println("方法前调用");
    }
    /******
     * 切入点的通知。这里直接写是切入点的方法名
     * 比如说 提交事务
     */
    @After("poinCut()")
    public void after(){
        System.out.println("方法后调用");
    }
}
