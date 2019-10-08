package fun.mytoys.ticket.movie.controller;


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
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public MovieController(RestTemplate restTemplate, DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
    }


    @GetMapping("/user/{id}")
    public UserVo findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://localhost:8080/" + id, UserVo.class);
    }

    /**
     * 查询microservice-simple-provider-user服务的信息并返回
     */
    @GetMapping("user-instances")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-simple-provider-user");
    }

    @GetMapping("/log-instances")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-simple-provider-user");
        log.info("{}:{}:{}",
                serviceInstance.getServiceId(),
                serviceInstance.getHost(),
                serviceInstance.getPort()
        );
    }
}
