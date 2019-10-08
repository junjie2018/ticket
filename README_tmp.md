#### microservice-provider-user

1.实现entity、dao、controller，使服务可以被访问

2.实现Eureka Client，使之可以注册到EurekaServer中

3.通过IDEA + spring.profiles.active实现快速启动多个user服务



#### microservice-provider-user-with-auth

1.实现entity、dao、controller，使服务可以被访问

2.实现Eureka Client，使之可以注册到EurekaServer中

3.通过IDEA + spring.profiles.active实现快速启动多个user服务

4.添加Spring Security依赖，实现该服务的安全功能：

​	a.实现UserDetailsService接口

​	b.实现UserDetail接口

​	c.配置类中配置HttpSecurity

​	d.配置类中配置AuthenticationManagerBuilder