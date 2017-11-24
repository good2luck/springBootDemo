package top.xudj.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by xudj on 17/11/24.
 */
@Configuration
//@EnableWebSecurity开启spring security功能
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置一些web安全的细节
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 不需要任何认证
                .antMatchers("/", "/home").permitAll()
                // 除了以上，其它的都需要认证
                .anyRequest().authenticated()
                .and()
            // 定义当需要用户登录时候，转到的登录页面
             .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
             .logout()
                .permitAll();
    }

    /**
     * 在内存中创建了一个用户，该用户的名称为user，密码为user，用户角色为USER
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user")
                .roles("USER");
    }
}
