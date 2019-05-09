/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.authservice.service.impl
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 18:32
 * @version
 */
package com.yuluhuang.authservice.service.impl;

import com.yuluhuang.authservice.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 18:32
 */
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username);
    }
}
