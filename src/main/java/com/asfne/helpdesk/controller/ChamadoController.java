package com.asfne.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asfne.helpdesk.domain.Chamado;
import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.ChamadoService;
import com.asfne.helpdesk.service.FuncionarioService;
import com.asfne.helpdesk.service.SetorService;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	@Autowired
	private SetorService setorService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
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
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("chamado", chamadoService.buscarPorId(id));
		return "chamado/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Chamado chamado, RedirectAttributes attributes) {
		chamadoService.editar(chamado);
		attributes.addFlashAttribute("mensagem", "Chamado editado com sucesso.");
		return "redirect:/chamados/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
		chamadoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Chamado excluido com sucesso.");
		return "redirect:/chamados/listar";
	}
	
	
	@ModelAttribute("setores")
	public List<Setor> listaDeSetores() {
		return setorService.buscarTodos();
	}
	
	@ModelAttribute("funcionarios")
	public List<Funcionario> listaDeFuncionarios() {
		return funcionarioService.buscarTodos();
	}
	
	@ModelAttribute("chamados")
	public List<Chamado> listaDeChamados() {
		return chamadoService.buscarTodos();
	}
	
}
