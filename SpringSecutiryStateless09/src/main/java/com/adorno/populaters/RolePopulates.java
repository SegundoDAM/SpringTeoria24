package com.adorno.populaters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adorno.model.ERole;
import com.adorno.model.RoleEntity;
import com.adorno.model.dtos.UserCreateDTO;
import com.adorno.repositories.RoleRepository;
import com.adorno.services.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class RolePopulates {
	private final RoleRepository roleRepository;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String bbddStatus;

	public RolePopulates(RoleRepository roleRepository, UserService userService) {
		super();
		this.roleRepository = roleRepository;
	}

	//debido al orden en que se cargan las clases de Spring
	//hay que asegurarse de que algunas cosas van al final
	@PostConstruct
	public void init() {
		if (!bbddStatus.equals("update")) {
			populateRoles();
		}
	}
	public void populateRoles() {
		for (ERole erole : ERole.values()) {
			if (roleRepository.findByName(erole).isEmpty())
				roleRepository.save(new RoleEntity(erole));
		}
	}
}
