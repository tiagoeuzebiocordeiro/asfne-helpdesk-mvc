package com.asfne.helpdesk.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.asfne.helpdesk.domain.Setor;

@Repository
public class SetorDaoImpl extends AbstractDao<Setor, Long> implements SetorDao {

	public List<Setor> findByNome(String nome) {
		
		return createQuery("select s from Setor s where s.nome like concat('%', ?1, '%')", nome);
		
		
	}
	
}
