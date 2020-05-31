package com.yuluhuang.authservice;

import com.yuluhuang.authservice.service.impl.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class AuthServiceApplication {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Configuration
    @EnableAuthorizationServer
    protected class OAUth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
        //		private TokenStore tokenStore = new InMemoryTokenStore();
        JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
        private static final String RESOURCE_ID = "order";

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserServiceDetail userServiceDetail;

        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }
        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            // 资源服务器使用同样的密钥
            converter.setSigningKey("123");
            return converter;
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//			super.configure(security);
            security
                    // 允许客户表单认证,不加的话/oauth/token无法访问
                    .allowFormAuthenticationForClients()
                    // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                    // 开启/oauth/token_key验证端口无权限访问
                    .tokenKeyAccess("permitAll()")
                    // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了
                    // 开启/oauth/check_token验证端口认证权限访问
                    .checkTokenAccess("isAuthenticated()");
        }


        // 我对于两种模式的理解便是，如果你的系统已经有了一套用户体系，每个用户也有了一定的权限，可以采用password模式
        // 如果仅仅是接口的对接，不考虑用户，则可以使用client模式
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // password 持多种编码，通过密码的前缀区分编码方式
            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

            clients.inMemory()
                    // password模式，自己本身有一套用户体系，在认证时需要带上自己的用户名和密码，以及客户端的client_id,client_secret
                    // 此时，accessToken所包含的权限是用户本身的权限，而不是客户端的权限
                    .withClient("browser")
                    .authorizedGrantTypes("refresh_token", "password")
                    .scopes("ui")
                    .and()
                    // client模式，没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请accessToken，
                    // 客户端有自己的client_id,client_secret对应于用户的username,password，而客户端也拥有自己的authorities，
                    // 当采取client模式认证时，对应的权限也就是客户端自己的authorities
                    .withClient("client_1")
                    .resourceIds(RESOURCE_ID)
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("select")
                    .authorities("ROLE_ADMIN")
                    .secret(finalSecret)
                    .and()
                    .withClient("service-hi")
                    .secret("{noop}123456")
                    .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                    .scopes("server");
        }

        // 定义授权和令牌端点以及令牌服务
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//             super.configure(endpoints);

            // redisTokenStore
            // endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
            //   .authenticationManager(authenticationManager)
            //   .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);


            endpoints
                    // 指定token存储位置
                    .tokenStore(tokenStore)
                    // 配置JwtAccessToken转换器
                    .tokenEnhancer(accessTokenConverter())
                    // 指定认证管理器
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userServiceDetail)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

            // 配置tokenServices参数
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setTokenStore(endpoints.getTokenStore());
            // 是否支持刷新
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
            tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
            // 20分钟
            tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(20));
            endpoints.tokenServices(tokenServices);
        }


    }
}
