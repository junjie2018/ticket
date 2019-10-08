package fun.mytoys.ticket.movie.client;

import fun.mytoys.ticket.feign.MicroserviceUserFeignConfig;
import fun.mytoys.ticket.movie.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@FeignClient(name = "microservice-user", configuration = MicroserviceUserFeignConfig.class)
@FeignClient(name = "microservice-user")
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserVo findById(@PathVariable("id") Long id);

    /**
     * Get请求包含多个参数
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    UserVo get1(@RequestParam("id") Long id, @RequestParam("username") String username);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    UserVo get2(@RequestParam Map<String, Object> amp);

    /**
     * Post请求包含多个参数
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    UserVo post(@RequestBody UserVo user);
}
