package com.adorno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adorno.model.dtos.PersonaResumenDTO;
import com.adorno.model.entities.Persona;
import com.adorno.model.mappers.PersonaResumenDTOMapper;

@Service
public class PersonaService {
	// Esto hace de repo
	ArrayList<Persona> personas;
	private boolean resultado = false;
	private final PersonaResumenDTOMapper personaResumenDTOMapper;

	public PersonaService(PersonaResumenDTOMapper personaResumenDTOMapper) {
		personas = new ArrayList<>();
		personas.add(new Persona(1, "Ramos", 32, "azur"));
		personas.add(new Persona(2, "Elias", 24,"ceruleo"));
		personas.add(new Persona(3, "Julian", 24,"celeste"));
		personas.add(new Persona(4, "Roberto", 24,"marino"));
		this.personaResumenDTOMapper=personaResumenDTOMapper;
	}

	public ArrayList<Persona> getPersonas() {
		return this.personas;
	}

	public boolean addPersona(Persona persona) {
		return this.personas.add(persona);
	}

//	public boolean update(int id, RequestUpdatePersona persona) {
//		findById(id).ifPresentOrElse(
//		(per) -> {
//			persona.update(per);
//			resultado = true;
//		}, 
//		() -> {
//			resultado = false;
//		});
//		return resultado;
//	}

	private Optional<Persona> findById(int id) {
		return personas.stream().filter((per) -> per.getId() == id).findFirst();
	}

	public boolean delete(int id) {
		return personas.removeIf((per) -> per.getId() == id);
	}

	public List<PersonaResumenDTO> getPersonasResumen() {
		return getPersonas().stream()
				.map((perso)->{
			return personaResumenDTOMapper.map(perso);
		}).collect(Collectors.toList());
	}
}
