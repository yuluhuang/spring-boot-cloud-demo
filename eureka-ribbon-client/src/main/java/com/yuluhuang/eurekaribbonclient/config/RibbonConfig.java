/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekaribbonclient.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 10:18
 * @version
 */
package com.yuluhuang.eurekaribbonclient.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 10:18
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
