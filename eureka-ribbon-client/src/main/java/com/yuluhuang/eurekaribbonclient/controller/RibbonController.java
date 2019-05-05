/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekaribbonclient.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 10:23
 * @version
 */
package com.yuluhuang.eurekaribbonclient.controller;

import com.yuluhuang.eurekaribbonclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 10:23
 */
@RestController
public class RibbonController {
    @Autowired
    RibbonService ribbonService;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/hi")
    public String hi(@RequestParam(required = false, defaultValue = "forezp") String name) {
        return ribbonService.hi(name);
    }

    @GetMapping("/testRibbon")
    public String testRibbon() {
        ServiceInstance instance = loadBalancer.choose("eureka-client");
        return instance.getHost() + ":" + instance.getPort();
    }
}
