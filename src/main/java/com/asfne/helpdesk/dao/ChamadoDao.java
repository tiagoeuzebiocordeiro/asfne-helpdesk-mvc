package com.asfne.helpdesk.dao;

import java.util.List;

import com.asfne.helpdesk.domain.Chamado;

public interface ChamadoDao {

	void save(Chamado chamado);
	
	void update(Chamado chamado);
	
	void delete(Long id);
	
	Chamado findById(Long id);
	
	List<Chamado> findAll();
	
}
