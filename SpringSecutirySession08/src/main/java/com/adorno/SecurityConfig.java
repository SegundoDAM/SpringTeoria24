package com.adorno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// Un bean que gnera un FILTRO aplicable a la seguridad de Spring
//	el filtro acepta una configuracion sobre httpsecurity para agregar o modificar
	// la seguridad por defecto
	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				// cross site request forgery
				/*
				 * El usuario desde el navegador hace una peticion que es interceptada por otro
				 * y este puede obtener las credenciales del usuario. PAra el trabajo con
				 * formularios en el navegador es indispensable. Lo deshabilitamos para poder
				 * trabajar sin navegador, por ejemplo la comunicacion entre apps.
				 */
				.csrf((csrf -> csrf.disable())).authorizeHttpRequests((auth) -> {
					auth.requestMatchers("v1/index2").permitAll();
					auth.anyRequest().authenticated();
				}).httpBasic((ht) -> {
				}).build();
	}
}
