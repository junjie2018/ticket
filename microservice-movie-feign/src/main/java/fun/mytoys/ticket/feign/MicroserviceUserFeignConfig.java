package fun.mytoys.ticket.feign;

import org.springframework.context.annotation.Configuration;

/**
 * 该类为Feign的配置类
 */
@Configuration
public class MicroserviceUserFeignConfig {
    // 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }

    // 实验log
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }

    // 实验为指定Feign客户端禁用Hystrix
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
