/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.authservice.entity
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 19:49
 * @version
 */
package com.yuluhuang.servicehi.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 19:49
 */
@Entity
public class Role  implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
