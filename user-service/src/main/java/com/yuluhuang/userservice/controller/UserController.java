/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.controller
 * @Description
 * @author yoshikouamari
 * @date 2019-05-06 17:59
 * @version
 */
package com.yuluhuang.userservice.controller;

import com.yuluhuang.userservice.dto.UserLoginDTO;
import com.yuluhuang.userservice.entity.User;
import com.yuluhuang.userservice.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-06 17:59
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi() {
        return port;
    }


    @Autowired
    UserServiceDetail userServiceDetail;
    @PostMapping("/register")
    public User postUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userServiceDetail.insertUser(username, password);
    }

    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userServiceDetail.login(username, password);
    }
}
