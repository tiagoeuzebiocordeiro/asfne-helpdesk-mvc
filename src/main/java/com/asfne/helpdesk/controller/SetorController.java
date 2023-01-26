package com.asfne.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.SetorService;

@Controller
@RequestMapping("/setores")
public class SetorController {

	@Autowired
	SetorService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Setor setor) {
		return "/setor/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("setores", service.buscarTodos());
		return "/setor/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Setor setor, RedirectAttributes redirectAttributes) {
		service.salvar(setor);
		redirectAttributes.addFlashAttribute("mensagem", "Setor cadastrado com sucesso.");
		return "redirect:/setores/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("setor", service.buscarPorId(id));
		return "/setor/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Setor setor, RedirectAttributes redirectAttributes) {
		service.editar(setor);
		redirectAttributes.addFlashAttribute("mensagem", "Setor editado com sucesso.");
		return "redirect:/setores/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("mensagemExcluir", "Setor excluido com sucesso.");
		return listar(model);
	}
	
}
