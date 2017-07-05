package com.way2learnonline;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	/*@Autowired
	private TokenStore tokenStore;*/
	
	
	// TODO -1 Configure RemoteToken Service by uncomenting the below method
		
	/*	@Bean
		public RemoteTokenServices tokenService(){
			RemoteTokenServices tokenService = new RemoteTokenServices();
			tokenService.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
			tokenService.setClientId("way2learn");
			tokenService.setClientSecret("way2learn");
			
			return tokenService;
		}*/
	
	@Override 
    public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		//TODO-2 Configure the resource id as w2l . this w2l is same as the resource id configure in our DB
		//resources.resourceId("w2l");
		// TODO -4 Configure Resource Server to point to the token store by uncommenting the belwo code
		//resources.tokenStore(tokenStore());
		
		super.configure(resources);
	}
	
	// TODO-3 Configure JDBC Token Store by uncommenting below code
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	}
}
