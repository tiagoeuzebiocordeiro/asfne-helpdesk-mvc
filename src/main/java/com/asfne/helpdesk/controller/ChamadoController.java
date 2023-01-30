package com.asfne.helpdesk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asfne.helpdesk.domain.Chamado;
import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.ChamadoService;
import com.asfne.helpdesk.service.FuncionarioService;
import com.asfne.helpdesk.service.SetorService;

import groovyjarjarcommonscli.ParseException;

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
	public String salvar(@Valid Chamado chamado, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "chamado/cadastro";
		}
		
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
	public String editar(@Valid Chamado chamado,BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "chamado/cadastro";
		}
		
		
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
	
	@GetMapping("/buscar/titulo")
	public String getPorTitulo(@RequestParam("titulo") String titulo, ModelMap model) {
		model.addAttribute("chamados", chamadoService.buscarPorTitulo(titulo));
		return "/chamado/lista";
	}
	
	@GetMapping("/buscar/setor")
	public String getPorSetor(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("chamados", chamadoService.buscarPorSetor(id));
		return "/chamado/lista";
	}
	
//	@GetMapping("/buscar/data")
//	public String getPorData(@RequestParam(name = "data", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, ModelMap model) throws ParseException {
//		model.addAttribute("chamados", chamadoService.buscarPorData(data));
//		return "/chamado/lista";
//	}
	
	@GetMapping("/buscar/data")
	public ModelAndView buscarPorData(@RequestParam("txtdata") String txtdata) throws ParseException {
		
		ModelAndView modelAndView = new ModelAndView("/chamado/lista");
		List<Chamado> lista = null;
		Date data = null;
		
		if (txtdata.equals("")) {
			lista = chamadoService.buscarTodos();
		} else {
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			try {
				data = formato.parse(txtdata);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			// muda aqui para o teu metodo que busca pela data
			lista = chamadoService.buscarPorData(data);
			
		}
		
		modelAndView.addObject("chamados", lista);
		return modelAndView;
		
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
