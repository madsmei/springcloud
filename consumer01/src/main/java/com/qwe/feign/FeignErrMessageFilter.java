package com.qwe.feign;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/*****
 * Feign的过滤器。固定写法，
 * 如果调用服务出现异常。会先到这里。然后再去到降级的逻辑 {@link StudentServiceFallbackFactory}中
 */
@Configuration
public class FeignErrMessageFilter {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    /*
    * 当调用服务时，如果服务返回的状态码不是200，就会进入到Feign的ErrorDecoder中
    * {"timestamp":"2020-02-17T14:01:18.080+0000","status":500,"error":"Internal Server Error","message":"/ by zero","path":"/feign/student/errorMessage"}
    * 只有这种方式才能获取所有的被feign包装过的异常信息
    *
    * 这里如果创建的Exception是HystrixBadRequestException
    * 则不会走熔断逻辑，不记住熔断统计
    * */
    class FeignErrorDecoder implements ErrorDecoder {

        private Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

        @Override
        public Exception decode(String s, Response response) {

            //这里是吧 Feign封装的异常信息。转换成 运行异常，，也可以转换成 我们自己的异常类。统一处理业务逻辑
            RuntimeException runtimeException = null;

            try {
                String retMsg = Util.toString(response.body().asReader());
                logger.info(retMsg);

                runtimeException = new RuntimeException(retMsg);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return runtimeException;
        }
    }
}
