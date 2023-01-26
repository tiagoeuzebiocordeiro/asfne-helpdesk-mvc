package com.asfne.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.FuncionarioService;
import com.asfne.helpdesk.service.SetorService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	@Autowired
	private SetorService setorService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", service.buscarTodos());
		return "/funcionario/lista";
	}
	
	/*@ModelAttribute("funcionarios")
	public List<Funcionario> listaDeSetores() {
		return service.buscarTodos();
	}*/
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attributes) {
		service.salvar(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	// Ver os setores disponiveis  no combo box para inserção de um funcionario
	
	@ModelAttribute("setores")
	public List<Setor> getSetores() {
		return setorService.buscarTodos();
	}
	
}
