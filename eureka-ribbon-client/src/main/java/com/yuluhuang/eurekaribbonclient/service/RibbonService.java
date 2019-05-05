/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekaribbonclient.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 10:20
 * @version
 */
package com.yuluhuang.eurekaribbonclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.hystrix.FallbackHandler;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.Map;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-04 10:20
 */
@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ", sorry, error!";
    }

}
