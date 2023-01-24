package com.asfne.helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asfne.helpdesk.dao.SetorDao;
import com.asfne.helpdesk.domain.Setor;



@Service
@Transactional(readOnly = false)
public class SetorServiceImpl implements SetorService {

	@Autowired
	SetorDao dao;
	
	@Override
	public void salvar(Setor setor) {
		dao.save(setor);
		
	}

	@Override
	public void editar(Setor setor) {
		dao.update(setor);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Setor buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Setor> buscarTodos() {
		return dao.findAll();
	}

}
