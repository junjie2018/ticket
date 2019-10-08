package fun.mytoys.ticket.movie.client;

import fun.mytoys.ticket.movie.vo.UserVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserVo findById(@PathVariable("id") Long id);
}
