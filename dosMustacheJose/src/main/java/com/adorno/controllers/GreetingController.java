package com.adorno.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adorno.services.UserService;


@Controller
public class GreetingController {

	@Autowired
	private UserService usersService;

	@RequestMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("name", usersService.getNumUsers() + " users");
		return "greeting_template";
	}

	@RequestMapping("/list")
	public String iteration(Model model) {
		model.addAttribute("colors", usersService.getColors());
		return "list_template";
	}

}
