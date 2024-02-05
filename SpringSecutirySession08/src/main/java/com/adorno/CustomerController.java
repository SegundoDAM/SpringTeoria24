package com.adorno;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CustomerController {

	private final SessionRegistry sessionRegistry;

	public CustomerController(SessionRegistry sessionRegistry) {
		super();
		this.sessionRegistry = sessionRegistry;
	}

//	Aqui aplicamos la seguridad
	@GetMapping("index")
	public String index() {
		return "hello secured";
	}

//	aqui no seguridad
	@GetMapping("index2")
	public String index2() {
		return "hello unsecured";
	}

	// al parecer este endpoint se hace para poder ver la infgormacion de la session
	// que ha sido registrada
	@GetMapping("session")
	public ResponseEntity<?> getRegistrySession() {
		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		User user = (User) allPrincipals.stream().filter(sess -> {
			return sess instanceof User;
		}).findFirst().get();
		List<SessionInformation> allSessions = sessionRegistry.getAllSessions(user, true);
		HashMap<String, Object> response = new HashMap<>();
		response.put("sessionId", allSessions.get(allSessions.size() - 1).getSessionId());
		response.put("user", user);
		return ResponseEntity.ok(response);
	}
}
