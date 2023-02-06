package com.contrachequeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contrachequeweb.model.Role;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {	
	
	@Query("select r from Role r where r.nomeRole = ?1")
	Role retornaRolePorNome(String nomeRole);
	
}
