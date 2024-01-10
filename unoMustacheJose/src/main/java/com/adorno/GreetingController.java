package com.adorno;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

	@RequestMapping("/greeting")
	public String greetingApp(Model model) {
		model.addAttribute("name", "Estebancito");
		model.addAttribute("silent", true);
		return "greeting";
	}

}
