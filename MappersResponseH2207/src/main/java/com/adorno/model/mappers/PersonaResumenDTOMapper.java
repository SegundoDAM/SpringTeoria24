package com.adorno.model.mappers;

import com.adorno.model.dtos.PersonaResumenDTO;
import com.adorno.model.entities.Persona;

//Como crear este elemento
public class PersonaResumenDTOMapper implements MyMapper<PersonaResumenDTO,Persona>{

	@Override
	public PersonaResumenDTO map(Persona s) {
		return new PersonaResumenDTO(s.getNombre(), s.getEdad());
	}

}
