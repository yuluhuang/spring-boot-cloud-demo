package com.yuluhuang.authservice;

import com.yuluhuang.authservice.service.impl.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

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

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;


		@Autowired
		private UserServiceDetail userServiceDetail;

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//			super.configure(security);
			security.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()");
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("browser")
					.authorizedGrantTypes("refresh_token", "password")
					.scopes("ui")
					.and()
					.withClient("service-hi")
					.secret("{noop}123456")
					.authorizedGrantTypes("client_credentials", "refresh_token", "password")
					.scopes("server");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//			super.configure(endpoints);
			endpoints.tokenStore(tokenStore)
					.authenticationManager(authenticationManager)
					.userDetailsService(userServiceDetail);
		}
























	}
}
