package com.adorno.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adorno.model.dtos.PersonaResumenDTO;
import com.adorno.services.PersonaService;

@RestController
@RequestMapping("personal")
public class PersonaController {
	private final PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		super();
		this.personaService = personaService;
	}
	@GetMapping("personas")
	public ResponseEntity<List<PersonaResumenDTO>> getPersonas() {
		List<PersonaResumenDTO> personasResumen = personaService.getPersonasResumen();
		return ResponseEntity.ok(personasResumen);
	}
}
