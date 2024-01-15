package com.adorno.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserService {
	List<String> colors = Arrays.asList("Red", "Blue", "Green");

	public int getNumUsers() {
		return 5;
	}

	public List<String> getColors() {
		return colors;
	}
}
