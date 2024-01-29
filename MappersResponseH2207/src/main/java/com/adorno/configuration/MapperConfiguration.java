package com.adorno.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adorno.model.mappers.PersonaResumenDTOMapper;

@Configuration
public class MapperConfiguration {
	
	@Bean
	public PersonaResumenDTOMapper getPersonaResumenDTOMapper() {
		return new PersonaResumenDTOMapper();
	}

}
