package com.qwe.health;

import com.qwe.controller.Consumer01Controller;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 服务健康状态检查,只有 服务状态 是UP的，才是服务正常的。才是值的我们去 调用的
 * @Date 2020/4/25
 * @Version V1.0
 * @Author Mads
 **/
@Configuration
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        if(Consumer01Controller.canVisiDB) {
            return new Health.Builder(Status.UP).build();
        }
        return new Health.Builder(Status.DOWN).build();
    }
}
