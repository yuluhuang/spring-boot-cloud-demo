/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.exception
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:27
 * @version
 */
package com.yuluhuang.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 19:27
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {
    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
