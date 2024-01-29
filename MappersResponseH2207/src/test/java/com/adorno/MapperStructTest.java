package com.adorno;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adorno.model.dtos.PersonaDTO;
import com.adorno.model.entities.Persona;
import com.adorno.model.mappers.PersonaMapper;

@SpringBootTest
class MapperStructTest {
	@Autowired
	PersonaMapper personaMapper;

	@Test
	void test() {
		Persona persona = new Persona("luis", 18,"amarillo");
		PersonaDTO dto= 
				personaMapper.mapToDTO(persona);
		
		Persona entity=personaMapper.mapToEntity(dto);
		assertEquals(entity.getNombre(), dto.getNombre());
	}

}
