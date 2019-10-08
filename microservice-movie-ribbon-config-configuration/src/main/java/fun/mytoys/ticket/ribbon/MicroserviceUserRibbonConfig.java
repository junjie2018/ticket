package fun.mytoys.ticket.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用JavaConfig配置Ribbon
 */
@Configuration
public class MicroserviceUserRibbonConfig {
    @Bean
    public IRule ribbonRule(){
        // 负载均衡规则为随机分配
        return new RandomRule();
    }
}
