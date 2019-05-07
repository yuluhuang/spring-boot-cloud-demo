/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekaclient.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-03 20:02
 * @version
 */
package com.yuluhuang.eurekaclient2.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-03 20:02
 */
@RestController
public class HiController {
    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam String name) {
        return "hi " + name + " i am from port:" + port;
    }


    public String hiError(String name) {
        return "hi," + name + " , sorry, error!";
    }

}
