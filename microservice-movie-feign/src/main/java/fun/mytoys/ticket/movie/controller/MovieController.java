package fun.mytoys.ticket.movie.controller;


import fun.mytoys.ticket.movie.client.UserFeignClient;
import fun.mytoys.ticket.movie.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class MovieController {

    private final UserFeignClient userFeignClient;

    @Autowired
    public MovieController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @GetMapping("/user/{id}")
    public UserVo findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }
}
