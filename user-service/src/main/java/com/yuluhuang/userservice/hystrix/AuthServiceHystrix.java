/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.hystrix
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:09
 * @version
 */
package com.yuluhuang.userservice.hystrix;

import com.yuluhuang.userservice.service.AuthServiceClient;
import com.yuluhuang.userservice.vo.JWT;
import org.springframework.stereotype.Component;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:09
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
