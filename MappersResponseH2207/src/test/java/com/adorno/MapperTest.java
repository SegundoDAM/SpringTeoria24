package com.adorno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adorno.model.dtos.PersonaResumenDTO;
import com.adorno.model.entities.Persona;
import com.adorno.model.mappers.PersonaResumenDTOMapper;

@SpringBootTest
class MapperTest {

	@Autowired
	PersonaResumenDTOMapper personaResumenDTOMapper;
	@Test
	void test() {
		String nombre = "Esteban";
		PersonaResumenDTO map = personaResumenDTOMapper.map(new Persona(nombre, 12, "Ceruleo"));
		assertEquals(map.getNombre(), nombre);
	}

}
