package com.asfne.helpdesk.service;

import java.util.List;

import com.asfne.helpdesk.domain.Chamado;

public interface ChamadoService {

	void salvar(Chamado chamado);
	
	void editar(Chamado chamado);
	
	void excluir(Long id);
	
	Chamado buscarPorId(Long id);
	
	List<Chamado> buscarTodos();
}
