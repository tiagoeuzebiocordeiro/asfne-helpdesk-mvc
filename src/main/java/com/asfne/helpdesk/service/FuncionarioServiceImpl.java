package com.asfne.helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asfne.helpdesk.dao.FuncionarioDao;
import com.asfne.helpdesk.domain.Funcionario;


@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioDao dao;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(Funcionario funcionario) {
		dao.update(funcionario);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		 return dao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscarPorSetor(Long id) {
		return dao.findBySetorId(id);
	}

}
