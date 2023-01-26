package com.asfne.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/funcionario/lista";
	}
	
	@ModelAttribute("funcionarios")
	public List<Funcionario> listaDeSetores() {
		return service.buscarTodos();
	}
	
	
}
