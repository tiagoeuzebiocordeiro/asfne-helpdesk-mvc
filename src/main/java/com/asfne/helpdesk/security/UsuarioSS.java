package com.asfne.helpdesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asfne.helpdesk.domain.Usuario;
import com.asfne.helpdesk.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioSS implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario retornaUsuarioLogado() {
		UserDetails activeUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioRepository.findUserByLogin(activeUser.getUsername());
	}
	
}
