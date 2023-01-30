package com.asfne.helpdesk.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.asfne.helpdesk.domain.Chamado;

public interface ChamadoDao {

	void save(Chamado chamado);
	
	void update(Chamado chamado);
	
	void delete(Long id);
	
	Chamado findById(Long id);
	
	List<Chamado> findAll();
	
	List<Chamado> buscarPorTitulo(String titulo);

	List<Chamado> buscarPorSetor(Long id);

	List<Chamado> findByData(Date data);
	
}
