/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.eurekafeignclient.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:23
 * @version
 */
package com.yuluhuang.eurekafeignclient.controller;

import com.yuluhuang.eurekafeignclient.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-04 11:23
 */
@RestController
public class HiController {

    @Autowired
    HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "forezp", required = false) String name) {
        return hiService.sayHi(name);
    }
}
