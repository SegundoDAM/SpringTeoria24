package com.adorno.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.adorno.mappers.UserCreateDTO2UserMapper;
import com.adorno.model.ERole;
import com.adorno.model.RoleEntity;
import com.adorno.model.UserEntity;
import com.adorno.model.dtos.UserCreateDTO;
import com.adorno.repositories.RoleRepository;
import com.adorno.repositories.UserRepository;
import com.adorno.security.jwt.JwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserCreateDTO2UserMapper userCreateDTO2UserMapper;
	private final JwtUtils jwtUtils;

	

	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			UserCreateDTO2UserMapper userCreateDTO2UserMapper, JwtUtils jwtUtils) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userCreateDTO2UserMapper = userCreateDTO2UserMapper;
		this.jwtUtils = jwtUtils;
	}

	// igual es momento de usar Response
	public boolean createUser(UserCreateDTO userCreateDTO) {
		UserEntity user = userCreateDTO2UserMapper.map(userCreateDTO);
		if (userCreateDTO.roles() != null) {
			Set<RoleEntity> roles = fillRoles(userCreateDTO.roles());
			user.setRoles(roles);
		}
		if (userRepository.save(user) != null) {
			return true;
		}
		return false;
	}

	private Set<RoleEntity> fillRoles(String[] t) {
		if (ERole.validate(t)) {
			return Arrays.asList(t).stream()
					.map((rol) -> {
							ERole rolresult = ERole.getRole(rol).get();
							Optional<RoleEntity> byName = roleRepository.findByName(rolresult);
							RoleEntity roleEntity = byName.get();
							return roleEntity;
						})
					.collect(Collectors.toSet());
		}
		return null;
	}

	public boolean delete(String username) {
		Optional<UserEntity> byUsername = userRepository.findByUsername(username);
		userRepository.delete(byUsername.get());
//		userRepository.deleteByUsername(username);
		return true;
	}
	
	public void refreshingToken(HttpServletResponse response, String authorizationHeader) throws Exception{
		try {
			String refresh_token = authorizationHeader.substring("Bearer ".length());
	//		Aqui tenemos este objeto que contiene el nombre del codificador hmac384 y la propia secret key
			Algorithm algorithm = Algorithm.HMAC384(jwtUtils.getSignatureKey().getEncoded());
			//Aqui tenemos al verificador que va a usar este algoritmo, consturido con el algoritmo anterior
			JWTVerifier verifier = JWT.require(algorithm).build();
			//obtenemos el token descodificado si cumple lo anterior
			DecodedJWT decodedJWT = verifier.verify(refresh_token);
			///////////////////////////////////////////////////////////////////////////////////////
			String username = decodedJWT.getSubject();
			UserEntity user = getUserByUsername(username);
			String access_token = jwtUtils.generateAccessToken(username);
			Map<String, String> tokens = new HashMap<>();
			tokens.put("access_token", access_token);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		} catch (Exception e) {
			response.setHeader("error", e.getMessage());
			response.setStatus(FORBIDDEN.value());
			Map<String, String> error = new HashMap<>();
			error.put("error_message", e.getMessage());
			response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), error);
		}
	}

	public UserEntity getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
}
