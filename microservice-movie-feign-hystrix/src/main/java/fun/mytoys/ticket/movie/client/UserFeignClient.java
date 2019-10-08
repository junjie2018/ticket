package fun.mytoys.ticket.movie.client;


import fun.mytoys.ticket.movie.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "microservice-user", fallback = UserFeignClient.FeignClientFallback.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserVo findById(@PathVariable("id") Long id);

    @Component
    class FeignClientFallback implements UserFeignClient {

        @Override
        public UserVo findById(Long id) {
            return new UserVo(-1L, "默认用户");
        }
    }
}


