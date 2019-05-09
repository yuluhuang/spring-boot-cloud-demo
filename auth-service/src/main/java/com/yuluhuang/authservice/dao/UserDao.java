/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.authservice.dao
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 18:33
 * @version
 */
package com.yuluhuang.authservice.dao;

import com.yuluhuang.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-08 18:33
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
