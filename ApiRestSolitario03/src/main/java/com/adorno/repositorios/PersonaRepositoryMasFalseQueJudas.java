package com.adorno.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.adorno.modelo.Persona;

public class PersonaRepositoryMasFalseQueJudas implements PersonaRepository {

	List<Persona> personas;
	
	
	public PersonaRepositoryMasFalseQueJudas() {
		super();
		this.personas = new ArrayList<>();
	}

	@Override
	public boolean save(Persona persona) {
		return this.personas.add(persona);
	}

	@Override
	public boolean delete(Persona persona) {
		return this.personas.remove(persona);
	}

	@Override
	public Optional<Persona> findByDNI(String DNI) {
		return personas.stream()
				.filter(
						(person)->{return person.getDni().equals(DNI);})
				.findFirst();
	}

}
