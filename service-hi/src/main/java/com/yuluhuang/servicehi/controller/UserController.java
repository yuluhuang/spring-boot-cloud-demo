/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.servicehi.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:56
 * @version
 */
package com.yuluhuang.servicehi.controller;

import com.yuluhuang.servicehi.entity.User;
import com.yuluhuang.servicehi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return userService.create(user);
    }
}
