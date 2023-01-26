package com.asfne.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asfne.helpdesk.domain.Chamado;
import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.ChamadoService;
import com.asfne.helpdesk.service.SetorService;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	@Autowired
	private SetorService setorService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Chamado chamado) {
		return "/chamado/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/chamado/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Chamado chamado, RedirectAttributes attributes) {
		chamadoService.salvar(chamado);
		attributes.addFlashAttribute("mensagem", "Chamado inserido com sucesso.");
		return "redirect:/chamados/cadastrar";
	}
	
	@ModelAttribute("setores")
	public List<Setor> listaDeSetores() {
		return setorService.buscarTodos();
	}
	
}
