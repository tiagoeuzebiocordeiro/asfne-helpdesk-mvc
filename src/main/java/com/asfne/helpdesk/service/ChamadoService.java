package com.asfne.helpdesk.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.asfne.helpdesk.domain.Chamado;

public interface ChamadoService {

	void salvar(Chamado chamado);
	
	void editar(Chamado chamado);
	
	void excluir(Long id);
	
	Chamado buscarPorId(Long id);
	
	List<Chamado> buscarTodos();

	List<Chamado> buscarPorTitulo(String titulo);

	List<Chamado> buscarPorSetor(Long id);

	List <Chamado> buscarPorData(Date data);
}
