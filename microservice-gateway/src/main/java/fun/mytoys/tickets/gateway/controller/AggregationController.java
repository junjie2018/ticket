package fun.mytoys.tickets.gateway.controller;

import com.google.common.collect.Maps;
import fun.mytoys.tickets.gateway.aggregation.AggregationService;
import fun.mytoys.tickets.gateway.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

@Slf4j
@RestController
public class AggregationController {
    private final AggregationService aggregationService;

    @Autowired
    public AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String, UserVo>> aggregate(@PathVariable Long id) {
        Observable<HashMap<String, UserVo>> result = this.aggregateObservable(id);
        return this.toDefferedResult(result);
    }

    private Observable<HashMap<String, UserVo>> aggregateObservable(Long id) {
        return Observable.zip(
                this.aggregationService.getMovieUserByUserId(id),
                this.aggregationService.getMovieUserByUserId(id),
                (userVo, movieUserVo) -> {
                    HashMap<String, UserVo> map = Maps.newHashMap();
                    map.put("user", userVo);
                    map.put("movieUser", movieUserVo);
                    return map;
                }
        );
    }

    private DeferredResult<HashMap<String, UserVo>> toDefferedResult(Observable<HashMap<String, UserVo>> details) {
        DeferredResult<HashMap<String, UserVo>> result = new DeferredResult<>();

        details.subscribe(new Observer<HashMap<String, UserVo>>() {

            @Override
            public void onCompleted() {
                log.info("完成");
            }

            @Override
            public void onError(Throwable e) {
                log.error("发生错误...", e);
            }

            @Override
            public void onNext(HashMap<String, UserVo> stringUserVoHashMap) {
                result.setResult(stringUserVoHashMap);
            }
        });

        return result;
    }
}
