/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.exception
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:25
 * @version
 */
package com.yuluhuang.userservice.exception;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:25
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message) {
        super(message);
    }
}
