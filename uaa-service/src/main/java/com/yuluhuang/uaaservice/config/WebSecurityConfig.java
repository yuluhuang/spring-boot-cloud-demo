/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.adminserver.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-07 17:16
 * @version
 */
package com.yuluhuang.uaaservice.config;

import com.yuluhuang.authservice.service.impl.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-07 17:16
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private UserServiceDetail userServiceDetail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authExceptiopn) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userServiceDetail)
                .passwordEncoder(new BCryptPasswordEncoder());
    }




}
