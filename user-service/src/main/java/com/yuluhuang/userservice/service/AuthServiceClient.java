/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:07
 * @version
 */
package com.yuluhuang.userservice.service;


import com.yuluhuang.userservice.hystrix.AuthServiceHystrix;
import com.yuluhuang.userservice.vo.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:07
 */
@FeignClient(value = "uaa-service", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {
    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);
}
