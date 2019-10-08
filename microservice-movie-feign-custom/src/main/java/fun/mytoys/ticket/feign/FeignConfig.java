package fun.mytoys.ticket.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Feign的配置类
 */
@Configuration
public class FeignConfig {
    // 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
