/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekafeignclient.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:18
 * @version
 */
package com.yuluhuang.eurekafeignclient.service;

import com.yuluhuang.eurekafeignclient.config.FeignConfig;
import com.yuluhuang.eurekafeignclient.hystrix.HiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:18
 */
@FeignClient(value = "service-hi", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface EurekaClientFeign {
    @GetMapping(value = "/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
