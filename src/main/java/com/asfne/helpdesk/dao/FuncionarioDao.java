package com.asfne.helpdesk.dao;

import java.util.List;

import com.asfne.helpdesk.domain.Funcionario;

public interface FuncionarioDao {

	void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findBySetorId(Long id);
	
}
