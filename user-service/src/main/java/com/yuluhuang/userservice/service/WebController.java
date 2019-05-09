/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:33
 * @version
 */
package com.yuluhuang.userservice.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-09 19:33
 */
@RestController
@RequestMapping("/foo")
public class WebController {
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo() {
        return "i'm foo," + UUID.randomUUID().toString();
    }
}
