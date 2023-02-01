package com.contrachequeweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contrachequeweb.model.UsuarioRole;

@Transactional
@Repository
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long> {

	@Query("select r from UsuarioRole r where r.usuario.id = :idUsuario")
	public List<UsuarioRole> retornaRoleUsuario(@Param("idUsuario") Long idUsuario);

	@Query("select r from UsuarioRole r where r.usuario.id = :idUsuario and r.role.id = :idRole")
	public UsuarioRole retornaRoleUsuarioRole(@Param("idUsuario") Long idUsuario, @Param("idRole") Long idRole);
	
	@Query("select r from UsuarioRole r where r.usuario.id = :usuarioId")
	public List<UsuarioRole> retornaPermissoesUsuario(Long usuarioId);
	
}
