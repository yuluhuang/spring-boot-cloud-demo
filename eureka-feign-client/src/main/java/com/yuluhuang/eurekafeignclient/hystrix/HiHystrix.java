/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekafeignclient.hystrix
 * @Description
 * @author yoshikouamari
 * @date 2019-05-05 09:01
 * @version
 */
package com.yuluhuang.eurekafeignclient.hystrix;

import com.yuluhuang.eurekafeignclient.service.EurekaClientFeign;
import org.springframework.stereotype.Component;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-05 09:01
 */
@Component
public class HiHystrix implements EurekaClientFeign {
    @Override
    public String sayHiFromClientEureka(String name) {
        return "hi," + name + "!";
    }
}
