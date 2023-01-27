package com.asfne.helpdesk.dao;

import java.util.List;

import com.asfne.helpdesk.domain.Setor;

public interface SetorDao {

	void save(Setor setor);
	
	void update(Setor setor);
	
	void delete(Long id);
	
	Setor findById(Long id);
	
	List<Setor> findAll();

	List<Setor> findByNome(String nome);
	
}
