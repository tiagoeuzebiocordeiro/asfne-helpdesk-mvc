package com.asfne.helpdesk.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.asfne.helpdesk.domain.Chamado;

@Repository
public class ChamadoDaoImpl extends AbstractDao<Chamado, Long> implements ChamadoDao {

	public List<Chamado> buscarPorTitulo(String titulo) {
		
		return createQuery("select c from Chamado c where c.titulo like concat('%', ?1, '%')", titulo);
		
	}
	
	public List<Chamado> buscarPorSetor(Long id) {
		
		return createQuery("select c from Chamado c where c.setor.id = ?1", id);
		
	}

	
	public List<Chamado> findByData(Date dataAberturaChamado) {
		
		return createQuery("select c from Chamado c where c.dataAberturaChamado = ?1", dataAberturaChamado);
		
	}

}
