package com.adorno.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {

	@RequestMapping("dimeAlgo")
	//vamos a meter en el controller una informacion que viene de la peticion
	public String giveMe(Model model,@RequestParam String parametro) {
		model.addAttribute("parametro", parametro);
		return "saySomething";
	}
	
	@RequestMapping(value = "/procesarFormulario", method = RequestMethod.POST)
	public String processForm(@RequestParam String info, HttpSession session) {
		session.setAttribute("infoUser", info);
		return "resultado_formulario";
	}
	
	@RequestMapping("/mostrarDatos")
	public String mostrarDatos(Model model, HttpSession sesion) {
		model.addAttribute("infoUsuario", sesion.getAttribute("infoUser"));
		return "datos";
	}
}
