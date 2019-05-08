/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.adminserver.security
 * @Description
 * @author yoshikouamari
 * @date 2019-05-07 17:16
 * @version
 */
package com.yuluhuang.adminserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-07 17:16
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Page with login form is served as /login.html and does a POST on /login
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        // The UI does a POST on /logout on logout
        http.logout().logoutUrl("/logout");
        // The ui currently doesn't support csrf
        http.csrf().disable();

        // Requests for the login page and the static assets are allowed
        http.authorizeRequests()
                .antMatchers("/login.html", "/**/*.css", "/assets/**", "/third-party/**")
                .permitAll();
        // ... and any other request needs to be authorized
        http.authorizeRequests().antMatchers("/**").authenticated();

        // Enable so that the clients can authenticate via HTTP basic for registering
        http.httpBasic();
//        super.configure(http);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("USER", "ADMIN");
    }
}
