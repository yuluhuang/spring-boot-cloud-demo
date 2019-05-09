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
import com.yuluhuang.userservice.dto.UserLoginDTO;
import com.yuluhuang.userservice.entity.User;
import com.yuluhuang.userservice.exception.UserLoginException;
import com.yuluhuang.userservice.utils.BPwdEncoderUtil;
import com.yuluhuang.userservice.vo.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-09 14:10
 */
@Service
public class UserServiceDetail {
    @Autowired
    private UserDao userRepository;

    @Autowired
    AuthServiceClient client;

    public User insertUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);

    }

    public UserLoginDTO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new UserLoginException("error username");
        }
        if (!BPwdEncoderUtil.matches(password, user.getPassword())) {
            throw new UserLoginException("error password");
        }
        JWT jwt = client.getToken("Basic dXNlcilzZXJ2aWNlOjEyMzQ1Ng== ", "password", username, password);

        if (null == jwt) {
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;


    }
}
