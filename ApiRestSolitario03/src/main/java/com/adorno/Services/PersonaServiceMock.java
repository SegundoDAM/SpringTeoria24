package com.adorno.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adorno.modelo.Persona;

@Service
public class PersonaServiceMock implements PersonaService {

	@Override
	public boolean add(Persona persona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String DNI) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Persona> getByDNI(String DNI) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
