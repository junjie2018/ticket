package fun.mytoys.ticket.user.controller;

import fun.mytoys.ticket.user.dao.UserRespository;
import fun.mytoys.ticket.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private final UserRespository userRespository;

    @Autowired
    public UserController(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> result = this.userRespository.findById(id);
        return result.orElse(null);
    }
}
