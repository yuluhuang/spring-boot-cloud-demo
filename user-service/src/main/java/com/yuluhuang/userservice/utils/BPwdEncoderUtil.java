/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.utils
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:13
 * @version
 */
package com.yuluhuang.userservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:13
 */
public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String BCryptPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
