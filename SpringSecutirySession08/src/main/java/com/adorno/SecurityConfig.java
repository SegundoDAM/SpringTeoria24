package com.adorno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public AuthenticationSuccessHandler getSuccess() {
		return ((request, response, authentication) -> {
			response.sendRedirect("/v1/index");
		});
	}

	@Bean
	SessionRegistry createSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests((auth) -> {
			auth.requestMatchers("v1/index2").permitAll();
			auth.anyRequest().authenticated();
		})
				// todos tienen permiso de acceso al login
				.formLogin(login -> {
					login.permitAll();
					login.successHandler(getSuccess());
				}).sessionManagement(sess -> {
					// hay varios tipos

					// always siempre que no hay una session existente la creara, si la hay la
					// reutiliza
					sess.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
					// crea una session si es necesario si no hay session la crea
					// hacen casi lo mismo. este es mas restrictivo(?) evalua si hace falta crear la
					// sesion
					// sess.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
					// no creamos sesiones pero reutiliza las que haya
					// sess.sessionCreationPolicy(SessionCreationPolicy.NEVER);
					// no crea ni reutiliza. Todas las peticiones son atomicas
					// sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					// las sesiones permiten almacenar informacion sobre el usuario, en esa session
					/*
					 * }) /* Aquii podemos decir por defecto la uri a la que vamos si no se crea
					 * bien la session
					 */
					sess.invalidSessionUrl("/login");
					/*
					 * Cuantas sesiones a la vez? una o varias dependera del servidor
					 */
//								sess.maximumSessions(1);
//								Esto esta asi porque el metodo no aparece en sessionMangement
					// Esta informacion esta accesible. Creamos otro endpoiint
					//
					sess.maximumSessions(1).sessionRegistry(createSessionRegistry());
					// esto controla cuando ha expirado la session. esta quitada
//					sess.expiredURL();
					// Es una vulnerabilidad con las sesiones
					sess.sessionFixation()
							// ante un ataque genera otro id. y copiar los datos de la sesion a la nueva
							// sesion
							//.migrateSession();
							// igual que la anterior sin migrar los datos
							// .newSession();
							// no hacer nada (????!!!!) deshabilitamos este escudo
							.none();
							})
				.build();
	}
}
