/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.servicehi.dao
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:42
 * @version
 */
package com.yuluhuang.userservice.dao;

import com.yuluhuang.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:42
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
