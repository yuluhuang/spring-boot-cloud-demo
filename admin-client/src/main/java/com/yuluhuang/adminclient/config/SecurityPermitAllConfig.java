/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.adminclient.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 16:18
 * @version
 */
package com.yuluhuang.adminclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 16:18
 */
@Configuration
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}
