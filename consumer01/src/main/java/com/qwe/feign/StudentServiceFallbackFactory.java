package com.qwe.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/*****
 * 这个降级   可以获取到 方法执行期间的异常信息
 */
@Component
public class StudentServiceFallbackFactory implements FallbackFactory<SchedualServiceHi> {

    @Override
    public SchedualServiceHi create(Throwable throwable) {

        if(throwable == null) {
            return null;
        }
        final String msg = throwable.getMessage();
        //服务提供者的异常信息
        System.out.println("exception:" + msg);
        return new SchedualServiceHi() {
            @Override
            public String sayHiFromClientOne() {
                return "降级了";
            }

            @Override
            public String sayHiHollo(String id) {
                return "降级了";
            }

            @Override
            public Object testPostUser(Object yyuser) {
                return null;
            }

            @Override
            public Object testGetUser(Object yyuser) {
                return null;
            }

            @Override
            public Object testGetUser(String id, String name, String adress) {
                return null;
            }
        };
    }
}
