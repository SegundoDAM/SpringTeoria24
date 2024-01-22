package com.adorno.Services;

import org.springframework.stereotype.Service;

@Service
public class ComprobadorServiceImpl implements ComprobadorService {

	private final PersonaService personaService;
	
	public ComprobadorServiceImpl(PersonaService personaService) {
		super();
		this.personaService = personaService;
	}

	@Override
	public boolean isMayorEdad(String dni) {
		return this.personaService.getByDNI(dni).get().getEdad()>=18;
	}

}
