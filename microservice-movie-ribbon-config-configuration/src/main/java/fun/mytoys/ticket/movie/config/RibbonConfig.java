package fun.mytoys.ticket.movie.config;

import fun.mytoys.ticket.ribbon.MicroserviceUserRibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用RibbonClient，为特定name的Ribbon Client自定义配置
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类
 */


@Configuration
public class RibbonConfig {
    @Configuration
    @RibbonClient(name = "microservice-user", configuration = MicroserviceUserRibbonConfig.class)
    public static class MicroserviceUser{ }
}
