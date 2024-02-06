package com.adorno.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf((cs) -> cs.disable())
				.authorizeHttpRequests((auth) -> {
						auth.requestMatchers("users/hello").permitAll();
						auth.anyRequest().authenticated();
					})
				.sessionManagement((sess) -> {
						sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					})
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	// Le estamos diciendo que el service de manipualcion de usuarios
	// usa la clase USerDetails (de Spring) para definir usuarios y mageja
	// sus datos con este servicio
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		String password = passwordEncoder().encode("1234");
		UserDetails user = User.withUsername("jose").password(password).roles().build();
		manager.createUser(user);
//		System.out.println("SecurityConfig: "+user.getPassword());
		return manager;
	}

	/*
	 * Este es el codificador que vamos a usar
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Cuando un usuario intente autenticarse en el sistema, nos dara sus
	 * credenciales (username/password) y este manager usara el USerDetailsManager
	 * para saber si hay un usuario con esas credenciales en el sistema
	 */
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
			throws Exception {
		return httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}
}
