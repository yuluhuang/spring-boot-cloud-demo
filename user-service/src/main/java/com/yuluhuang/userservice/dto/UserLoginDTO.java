/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.dto
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:24
 * @version
 */
package com.yuluhuang.userservice.dto;

import com.yuluhuang.userservice.entity.User;
import com.yuluhuang.userservice.vo.JWT;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:24
 */
public class UserLoginDTO {

    private JWT jwt;
    private User user;

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
