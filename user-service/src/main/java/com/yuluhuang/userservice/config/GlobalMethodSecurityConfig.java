/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:05
 * @version
 */
package com.yuluhuang.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:05
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig {
}
