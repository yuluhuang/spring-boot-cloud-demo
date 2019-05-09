/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.authservice.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:13
 * @version
 */
package com.yuluhuang.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:13
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }
}
