package com.asfne.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/chamado/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/chamado/lista";
	}
	
}
