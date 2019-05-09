/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.userservice.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:10
 * @version
 */
package com.yuluhuang.userservice.service;

import com.yuluhuang.userservice.dao.UserDao;
import com.yuluhuang.userservice.entity.User;
import com.yuluhuang.userservice.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-09 14:10
 */
@Service
public class UserServiceDetail {
    @Autowired
    private UserDao userRepository;
    public User insertUser(String username, String password) {
        User user= new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);

    }
}
