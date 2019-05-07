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
/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-07 17:16
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Page with login form is served as /login.html and does a POST on /login
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
//        // The UI does a POST on /logout on logout
//        http.logout().logoutUrl("/logout");
//        // The ui currently doesn't support csrf
//        http.csrf().disable();
//
//        // Requests for the login page and the static assets are allowed
//        http.authorizeRequests()
//                .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
//                .permitAll();
//        // ... and any other request needs to be authorized
//        http.authorizeRequests().antMatchers("/**").authenticated();
//
//        // Enable so that the clients can authenticate via HTTP basic for registering
//        http.httpBasic();
//        super.configure(http);
//
//    }
//}
