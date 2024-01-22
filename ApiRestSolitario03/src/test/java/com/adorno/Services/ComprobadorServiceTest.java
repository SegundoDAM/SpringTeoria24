package com.adorno.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.adorno.modelo.Persona;

@SpringBootTest
class ComprobadorServiceTest {

	@MockBean
	PersonaService personaService;
	
	@Autowired
	@InjectMocks
	ComprobadorServiceImpl comprobadorServiceImpl;

	@Test
	void testIsMayorEdad() {
		when(personaService.getByDNI("1")).thenReturn(Optional.of(new Persona("1","Luis",12)));
		Optional<Persona> humano=personaService.getByDNI("1");
		assertFalse(comprobadorServiceImpl.isMayorEdad("1"));
	}

}
