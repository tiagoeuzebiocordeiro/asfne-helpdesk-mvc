package com.asfne.helpdesk.service;

import java.util.List;

import com.asfne.helpdesk.domain.Setor;

public interface SetorService {


	void salvar(Setor setor);
	
	void editar(Setor setor);
	
	void excluir(Long id);
	
	Setor buscarPorId(Long id);
	
	List<Setor> buscarTodos();

	List<Setor> buscarPorNome(String nome);
	
}
