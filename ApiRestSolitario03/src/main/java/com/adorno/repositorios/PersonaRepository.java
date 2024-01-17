package com.adorno.repositorios;

import java.util.Optional;

import com.adorno.modelo.Persona;

public interface PersonaRepository {
	public boolean save(Persona persona);
	public boolean delete(Persona persona);
	public Optional<Persona> findByDNI(String DNI);
}
