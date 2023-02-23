package com.asfne.helpdesk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asfne.helpdesk.domain.Usuario;
import com.asfne.helpdesk.domain.UsuarioRole;
import com.asfne.helpdesk.repository.RoleRepository;
import com.asfne.helpdesk.repository.UsuarioRepository;
import com.asfne.helpdesk.repository.UsuarioRoleRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;
	
	@RequestMapping("/teste")
	public String home() {
		return "index";	
	}		

	@RequestMapping("/cadastrarusuario")
	public ModelAndView cadastrar() {
		
		ModelAndView modelAndView = new ModelAndView("usuario/novo-usuario");
		Usuario usuario = new Usuario();
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
		
	}	
	
	@RequestMapping("/novousuario")
	public ModelAndView cadastrarUsuario() {
		
		ModelAndView modelAndView = new ModelAndView("usuario/novo");
		Usuario usuario = new Usuario();
		modelAndView.addObject("usuario", usuario);
		
		return modelAndView;
		
	}	 	
	
	@GetMapping("/editarusuario/{id}")
	public ModelAndView editar(@PathVariable("id") Long idUsuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		
		ModelAndView modelAndView = new ModelAndView("usuario/edita-usuario");
		modelAndView.addObject("usuario", usuario.get());
		modelAndView.addObject("rolesUsuario", usuarioRoleRepository.retornaRoleUsuario(idUsuario));
		return modelAndView;
		
	}	
	   
	@GetMapping("/config-acessos/{id}")
	public ModelAndView configurarAcessos(@PathVariable("id") Long idUsuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

		ModelAndView modelAndView = new ModelAndView("usuario/permissoes");
		modelAndView.addObject("usuario", usuario.get());
		modelAndView.addObject("msg", new ArrayList<String>());
		modelAndView.addObject("roles", roleRepository.findAll());
		modelAndView.addObject("rolesUsuario", usuarioRoleRepository.retornaRoleUsuario(idUsuario));
		return modelAndView;		
		
	}
	
	@GetMapping("/removerusuario/{id}")
	public ModelAndView removerUsuario(@PathVariable("id") Long id) {
			
		usuarioRepository.deleteById(id);
		
		List<String> msg = new ArrayList<String>();
		msg.add("Usuario apagado com sucesso.");
		
		ModelAndView modelAndView = new ModelAndView("usuario/lista-usuarios");
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		return modelAndView;	
		
	}		
	
	@PostMapping("/gravarrole/{usuarioid}")
	public ModelAndView adicionaRoleUsuario(UsuarioRole usuarioRole , 
									 @PathVariable("usuarioid") Long usuarioid) {
		
		Usuario usuario = usuarioRepository.findById(usuarioid).get();
		
		UsuarioRole roleUser = usuarioRoleRepository.retornaRoleUsuarioRole(usuarioid, usuarioRole.getRole().getId());
		
		if(usuarioRole != null && usuarioRole.getRole().getNomeRole().isEmpty() 
				|| usuarioRole.getRole().getDescPerfil().isEmpty() 
				|| roleUser != null) {
			
			ModelAndView modelAndView = new ModelAndView("usuario/permissoes");
			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("msg", new ArrayList<String>());
			modelAndView.addObject("roles", roleRepository.findAll());
			modelAndView.addObject("rolesUsuario", usuarioRoleRepository.retornaRoleUsuario(usuarioid));
			
			List<String> msg = new ArrayList<String>();
			if (usuarioRole.getRole().getNomeRole().isEmpty()) {
				msg.add("Tipo de Permissão não informado");
			}
			
			if (roleUser != null) {
				msg.add("Tipo de permissão já cadastrada.");
			}
						
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
			
		} else {
		
			ModelAndView modelAndView = new ModelAndView("usuario/permissoes");
			
			List<String> msg = new ArrayList<String>();
	
			usuarioRole.setUsuario(usuario);		
			usuarioRoleRepository.save(usuarioRole);
			
			msg.add("Tipo de permissão gravada com sucesso.");
			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("roles", roleRepository.findAll());
			modelAndView.addObject("rolesUsuario", usuarioRoleRepository.retornaRoleUsuario(usuarioid));
			
			return modelAndView;
		
		}
		
	}	
	
	@GetMapping("/removerrole/{id}")
	public ModelAndView removerRoleUsuario(@PathVariable("id") Long id) {
		
		Usuario usuario = usuarioRoleRepository.findById(id).get().getUsuario();
		
		usuarioRoleRepository.deleteById(id);
		
		ModelAndView modelAndView = new ModelAndView("usuario/permissoes");
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roleRepository.findAll());
		modelAndView.addObject("rolesUsuario", usuarioRoleRepository.retornaRoleUsuario(usuario.getId()));
		return modelAndView;
		
	}	
		       
	@RequestMapping(method = RequestMethod.GET, value = "/usuarios")
	public ModelAndView listarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("usuario/lista-usuarios");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/consultausuarios")
	public ModelAndView listarUsuariosComParametros(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView modelAndView = new ModelAndView("usuario/lista-usuarios");
		List<Usuario> listaDeUsuarios = null;
		
		if (nomepesquisa.equals("")) {
			listaDeUsuarios = usuarioRepository.findAll();
		} else {
			listaDeUsuarios = usuarioRepository.consultaUsuarioPorNome(nomepesquisa);
		}
		
		modelAndView.addObject("usuarios", listaDeUsuarios);
		return modelAndView;
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "salvarusuario")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult bindingResult)	throws IOException {

		if (usuario.getId() != null) {
			Usuario usuarioAlteracao = usuarioRepository.retornaUsuarioPorId(usuario.getId());
			usuario.setRoles(usuarioAlteracao.getRoles());
		}
							
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senha = encoder.encode(usuario.getSenha());
		usuario.setSenha(senha);

		List<String> msg = new ArrayList<String>();
		
		Usuario usuarioJaCadastrado = null;
		
		// Existindo erros
		if (bindingResult.hasErrors()) {
			
			if (usuario.getId() != null) {
				
				ModelAndView modelAndView = new ModelAndView("usuario/edita-usuario");
				modelAndView.addObject("usuario", usuario);
				
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					msg.add(objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
				}				
				
				modelAndView.addObject("msg", msg);
				return modelAndView;
				
			} else {
				
				ModelAndView modelAndView = new ModelAndView("usuario/novo-usuario");
				modelAndView.addObject("usuario", usuario);
				
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					msg.add(objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
				}				
				
				modelAndView.addObject("msg", msg);
				return modelAndView;		
				
			}

		} else { // Não havendo erros
					
			if (usuario.getId() != null) {
				
				usuarioJaCadastrado = usuarioRepository.consultaUsuarioJaCadastrado(usuario.getId(), usuario.getLogin());

				if (usuarioJaCadastrado == null) {
									
					msg.add("Usuario alterado com sucesso.");
					ModelAndView andView = new ModelAndView("usuario/lista-usuarios");
					
					usuarioRepository.save(usuario);
					
					andView.addObject("msg", msg);
					andView.addObject("usuarios", usuarioRepository.findAll());
					return andView;
					
				} else {
					
					ModelAndView andView = new ModelAndView("usuario/edita-usuario");
					andView.addObject("usuario", usuario);
					msg.add("Já existe um usuário com este login.");
					andView.addObject("msg", msg);
					return andView;
					
				}
												
			} else {
				
				usuarioJaCadastrado = usuarioRepository.findUserByLogin(usuario.getLogin()); 
				
				if (usuarioJaCadastrado == null) {

					msg.add("Usuario gravado com sucesso.");
					ModelAndView andView = new ModelAndView("usuario/novo-usuario");
					
					usuarioRepository.save(usuario);
					
					andView.addObject("msg", msg);
					andView.addObject("usuario", new Usuario());	
					return andView;
					
				} else {
					
					ModelAndView andView = new ModelAndView("usuario/novo-usuario");
					andView.addObject("usuario", usuario);
					msg.add("Já existe um usuário com este login.");				
					andView.addObject("msg", msg);
					return andView;							
					
				}
				
			}
						
		}			

	}
	
	
    
}