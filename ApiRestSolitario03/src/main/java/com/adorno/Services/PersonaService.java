package com.adorno.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adorno.modelo.Persona;
import com.adorno.repositorios.PersonaRepository;
import com.adorno.repositorios.PersonaRepositoryMasFalseQueJudas;

@Service
public class PersonaService {

	private PersonaRepository peroRepository;

	public PersonaService() {
		super();
		peroRepository = new PersonaRepositoryMasFalseQueJudas();
	}

	public boolean add(Persona persona) {
		return peroRepository.save(persona);
	}

	public boolean delete(String DNI) {
		boolean isPresent = peroRepository.findByDNI(DNI).isPresent();
		if (isPresent)
			peroRepository.findByDNI(DNI).ifPresent((per) -> {
				peroRepository.delete(per);
			});
		return isPresent;
	}
	
	public Optional<Persona> getByDNI(String DNI){
		return peroRepository.findByDNI(DNI);
	}
}
