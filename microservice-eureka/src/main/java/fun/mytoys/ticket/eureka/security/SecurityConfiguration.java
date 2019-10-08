//package fun.mytoys.ticket.eureka.security;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.ribbon.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.ribbon.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.ribbon.annotation.web.builders.HttpSecurity;
//import org.springframework.security.ribbon.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.ribbon.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private CustomUserDetailService userDetailService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailService).passwordEncoder(this.passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated().and()
//                .httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // 明文编码器，就是不做任何操作的密码编辑器，是Spring提供给我们做明文测试的
//        // TODO 修改这部分
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Component
//    class CustomUserDetailService implements UserDetailsService {
//
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            if ("user".equals(username)) {
//                return new SecurityUser("user", "password1", "user-role");
//            }
//            if ("admin".equals(username)) {
//                return new SecurityUser("admin", "password2", "admin-role");
//            }
//            return null;
//
//        }
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    class SecurityUser implements UserDetails {
//        private static final long serialVersionUID = 1L;
//
//        private Long id;
//        private String username;
//        private String password;
//        private String role;
//
//        public SecurityUser(String username, String password, String role) {
//            super();
//            this.username = username;
//            this.password = password;
//            this.role = role;
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
//            authorities.add(authority);
//            return authorities;
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//    }
//}
