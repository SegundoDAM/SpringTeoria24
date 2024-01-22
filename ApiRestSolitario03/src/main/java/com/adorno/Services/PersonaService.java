package com.adorno.Services;

import java.util.Optional;

import com.adorno.modelo.Persona;

public interface PersonaService {
	public boolean add(Persona persona);

	public boolean delete(String DNI);
	
	public Optional<Persona> getByDNI(String DNI);
}
