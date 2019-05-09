/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.servicehi.config
 * @Description
 * @author yoshikouamari
 * @date 2019-05-08 20:36
 * @version
 */
package com.yuluhuang.uaaservice.config;

import com.yuluhuang.authservice.service.impl.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @author yoshikouamari
 * @Description
 * @date 2019-05-08 20:36
 */
@Configuration
@EnableConfigurationProperties
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceDetail userServiceDetail;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        clients.inMemory()
                .withClient("user-service")
                .secret("123456")
                .scopes("service")
                .authorizedGrantTypes("refresh_token", "password")
                .accessTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        super.configure(endpoints);
        endpoints.tokenStore(tokenStore).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager).userDetailsService(userServiceDetail);
    }


//    @Bean
//    public TokenStore tokenStore() {
//        return new JwkTokenStore("/", jwtTokenEnhancer());
//    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
        return converter;
    }
}
