package com.adorno.populaters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private final UserService userService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String bbddStatus;

	public RolePopulates(RoleRepository roleRepository, UserService userService) {
		super();
		this.roleRepository = roleRepository;
		this.userService = userService;
	}

	//debido al orden en que se cargan las clases de Spring
	//hay que asegurarse de que algunas cosas van al final
	@PostConstruct
	public void init() {
		if (!bbddStatus.equals("update")) {
			populateRoles();
			populateUsers();
		}
	}
	public void populateRoles() {
		for (ERole erole : ERole.values()) {
			if (roleRepository.findByName(erole).isEmpty())
				roleRepository.save(new RoleEntity(erole));
		}
	}
	public void populateUsers() {
		UserCreateDTO userCreateDTO = new UserCreateDTO("duque@dolor.es", "cayetano", new BCryptPasswordEncoder().encode("1234"), "ADMIN","USER");
		userService.createUser(userCreateDTO);
	}
}
