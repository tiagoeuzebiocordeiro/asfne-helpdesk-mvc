package com.asfne.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/*@GetMapping("/home")
	public String home() {
		return "home";
	}*/
	
	@RequestMapping("/home")
	public String home() {
	 return "home";	
	}	

	@RequestMapping("/login")
	public String login() {
	 return "login";	
	}	
	
	// login invalido 
	@GetMapping({"/login-error"}) // login?error=true
	public String loginError(ModelMap model) {
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Crendenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
		return "login";
	}	
	
	
}
