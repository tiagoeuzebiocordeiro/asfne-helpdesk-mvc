package com.asfne.helpdesk.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.domain.Setor;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	public List<Funcionario> findByNome(String nome) {
		
		return createQuery("select f from Funcionario f where f.nome like concat('%', ?1, '%')", nome);
		
	}
	
public List<Funcionario> findBySetorId(Long id) {
		
		return createQuery("select f from Funcionario f where f.setor.id = ?1", id);
		
	}
	
}
