/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekafeignclient.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:19
 * @version
 */
package com.yuluhuang.eurekafeignclient.config;

import feign.Request;
import feign.Retryer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-04 11:19
 */
@Configuration
public class FeignConfig {

    /**
     * 配置请求重试
     *
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(200, SECONDS.toMillis(2), 10);
    }


    /**
     * 设置请求超时时间
     *默认
     * public Options() {
     * this(10 * 1000, 60 * 1000);
     * }
     *
     */
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(60 * 1000, 60 * 1000);
    }



    /**
     * 打印请求日志
     * @return
     */
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
