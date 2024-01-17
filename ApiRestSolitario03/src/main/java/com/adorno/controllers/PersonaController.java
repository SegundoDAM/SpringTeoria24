package com.adorno.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adorno.Services.PersonaService;
import com.adorno.modelo.Persona;

@RestController
@RequestMapping("personas")
public class PersonaController {
	
	private final PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		super();
		this.personaService = personaService;
	}

	@GetMapping("busqueda")
	public Persona encontrarPersona() {
		return personaService.getByDNI("1").orElse(new Persona());
	}
}
