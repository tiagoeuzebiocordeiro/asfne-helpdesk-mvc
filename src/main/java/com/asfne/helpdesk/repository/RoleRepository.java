package com.asfne.helpdesk.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asfne.helpdesk.domain.Role;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("select r from Role r where r.nomeRole = ?1")
	Role retornaRolePorNome(String nomeRole);
	
}
