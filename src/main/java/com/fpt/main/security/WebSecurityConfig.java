package com.fpt.main.security;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	String[] pathArray = new String[] {			
								"/api/orders/**", 
								"/api/favourites/**",
								"/api/rating/**"
						};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(pathArray)
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		
		// add cors filter
		http.cors();
		
		// force a non-empty response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);
	}

}
