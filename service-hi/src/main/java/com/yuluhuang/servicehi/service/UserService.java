/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.servicehi.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:45
 * @version
 */
package com.yuluhuang.servicehi.service;


import com.yuluhuang.servicehi.entity.User;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-08 20:45
 */

public interface UserService {

    User create(User user);
}
