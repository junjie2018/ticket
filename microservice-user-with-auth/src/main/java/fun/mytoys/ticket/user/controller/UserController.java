package fun.mytoys.ticket.user.controller;

import fun.mytoys.ticket.user.dao.UserRespository;
import fun.mytoys.ticket.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {
    private final UserRespository userRespository;

    @Autowired
    public UserController(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority grantedAuthority : collection) {
                log.info("当前用户是{}，角色是{}", user.getUsername(), grantedAuthority.getAuthority());
            }
        } else {
            // Do something others
        }
        Optional<User> user = userRespository.findById(id);
        return user.orElse(null);
    }
}
