/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekafeignclient.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:21
 * @version
 */
package com.yuluhuang.eurekafeignclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-04 11:21
 */
@Service
public class HiService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    public String sayHi(String name) {
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
