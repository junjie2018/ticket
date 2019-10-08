package fun.mytoys.tickets.gateway.aggregation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import fun.mytoys.tickets.gateway.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Service
public class AggregationService {
    private final RestTemplate restTemplate;

    @Autowired
    public AggregationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<UserVo> getUserById(Long id) {
        return Observable.create(observer -> {
            UserVo userVo = restTemplate.getForObject("http://microservice-user/{id}",
                    UserVo.class,
                    id);
            observer.onNext(userVo);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<UserVo> getMovieUserByUserId(Long id) {
        return Observable.create(observer -> {
            UserVo moverUserVo = restTemplate.getForObject("http://microservice-movie/user/{id}",
                    UserVo.class,
                    id);
            observer.onNext(moverUserVo);
            observer.onCompleted();
        });
    }

    public UserVo fallback(Long id) {
        UserVo userVo = new UserVo();
        userVo.setId(-1L);
        return userVo;
    }
}
