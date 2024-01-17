package com.adorno;

import org.junit.jupiter.api.Test;

import com.adorno.modelo.Persona;
import com.adorno.modelo.Socio;

class PersonaTest {

	@Test
	void test() {
		Persona per=new Persona();
		Persona persona = Persona.builder().dni("").nombre("Eustaquio").edad((byte)12).build();
		Socio socio=new Socio("dd", "Faustino");
	}
	

}
