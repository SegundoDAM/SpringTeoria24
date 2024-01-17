package com.adorno.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("busqueda/{DNI}")
	public Persona encontrarPersona(@PathVariable String DNI) {
		return personaService.getByDNI(DNI).orElse(new Persona());
	}
	
	@PostMapping("insercion")
	public boolean insertar(@RequestBody Persona persona) {
		return personaService.add(persona);
	}
}
