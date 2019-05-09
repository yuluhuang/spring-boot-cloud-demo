/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.servicehi.service
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:43
 * @version
 */
package com.yuluhuang.servicehi.service;

import com.yuluhuang.servicehi.dao.UserDao;
import com.yuluhuang.servicehi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-08 20:43
 */
@Service
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;


    @Override
    public User create(User user) {
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        User u = userDao.save(user);
        return u;
    }
}
