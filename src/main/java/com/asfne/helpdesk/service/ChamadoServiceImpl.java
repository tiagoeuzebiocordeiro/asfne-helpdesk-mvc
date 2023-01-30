package com.asfne.helpdesk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asfne.helpdesk.dao.ChamadoDao;
import com.asfne.helpdesk.domain.Chamado;



@Service
@Transactional(readOnly = false)
public class ChamadoServiceImpl implements ChamadoService {

	@Autowired
	ChamadoDao dao;
	
	@Override
	public void salvar(Chamado chamado) {
		dao.save(chamado);
		
	}

	@Override
	public void editar(Chamado chamado) {
		dao.update(chamado);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Chamado buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Chamado> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public List<Chamado> buscarPorTitulo(String titulo) {
		return dao.buscarPorTitulo(titulo);
	}

	@Override
	public List<Chamado> buscarPorSetor(Long id) {
		return dao.buscarPorSetor(id);
	}

	@Override
	public List<Chamado> buscarPorData(Date data) {
		return dao.findByData(data);
	}

}
