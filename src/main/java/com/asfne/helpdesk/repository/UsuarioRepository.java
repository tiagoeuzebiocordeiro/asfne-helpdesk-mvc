package com.asfne.helpdesk.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asfne.helpdesk.domain.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);	

	@Query("select u from Usuario u where u.login = ?1")
	Usuario retornaUsuarioPorLogin(String login);	
	
	@Query("select u from Usuario u where u.id = ?1")
	Usuario retornaUsuarioPorId(Long id);		
	
	@Query("select u from Usuario u where u.nome like %?1% ")
	List<Usuario> consultaUsuarioPorNome(String nome);
	
	@Query("select u from Usuario u where u.id <> ?1 and u.login = ?2")
	Usuario consultaUsuarioJaCadastrado(Long id, String login);		
	
	/*
	 * Este código servirá para consulta no futuro
	 * pois a paginação sera feita no frontend
	 */
    @Query("select u from Usuario u where lower(u.nome) like %?1% ")          
    Page<Usuario> search(String nome, Pageable pageable);
		
}
