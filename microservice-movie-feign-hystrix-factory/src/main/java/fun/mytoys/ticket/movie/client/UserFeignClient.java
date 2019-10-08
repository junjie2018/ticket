package fun.mytoys.ticket.movie.client;


import feign.hystrix.FallbackFactory;
import fun.mytoys.ticket.movie.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "microservice-user", fallbackFactory = UserFeignClient.FeignClientFallbackFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserVo findById(@PathVariable("id") Long id);

    @Component
    @Slf4j
    class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
        @Override
        public UserFeignClient create(Throwable throwable) {
            return id -> {
                /**
                 * 将日志放在各个fallback方法中，否则会在应用启动时，打印该条消息
                 */
                log.info("fallback, reason was: {}", throwable);
                return new UserVo(-1L, "默认用户");
            };
        }
    }
}


