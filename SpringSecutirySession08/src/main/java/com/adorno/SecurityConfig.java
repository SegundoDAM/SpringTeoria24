package com.adorno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	public AuthenticationSuccessHandler getSuccess(){
		return ((request,response,authentication)->{
			response.sendRedirect("/v1/index");
		});
	}
	
	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.authorizeHttpRequests((auth) -> {
							auth.requestMatchers("v1/index2").permitAll();
							auth.anyRequest().authenticated();
						})
				//todos tienen permiso de acceso al login
				.formLogin(login -> {
					login.permitAll();
					login.successHandler(getSuccess());
					})
				.build();
	}
}
