package com.adorno.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.adorno.security.jwt.JwtAuthenticationFilter;
import com.adorno.security.jwt.JwtUtils;
import com.adorno.services.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtUtils jwtUtils;

	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JwtUtils jwtUtils) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtUtils = jwtUtils;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
			throws Exception {
		// aqui vamos a poner la autenticacion de jwt
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		// aqui el resto de la autenticacion
		DefaultSecurityFilterChain httpsec = httpSecurity.csrf((cs) -> cs.disable()).authorizeHttpRequests((auth) -> {
			auth.requestMatchers("users/hello").permitAll();
			auth.anyRequest().authenticated();
		}).sessionManagement((sess) -> {
			sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}).addFilter(jwtAuthenticationFilter).build();
		log.debug("SecurityConfig:terminando configuracion config");
		return httpsec;
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
		AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder).and().build();
		log.debug("SecurityConfig:generando autentication manager");
		return authenticationManager;
	}
}
