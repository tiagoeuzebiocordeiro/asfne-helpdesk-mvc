package com.asfne.helpdesk.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.asfne.helpdesk.domain.Funcionario;
import com.asfne.helpdesk.domain.Setor;
import com.asfne.helpdesk.service.FuncionarioService;


@Component
public class StringToFuncionarioConverter implements Converter<String, Funcionario> {

	@Autowired
	private FuncionarioService service;
	
	@Override
	public Funcionario convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		
		Long id  = Long.valueOf(text);
		
		return service.buscarPorId(id);
	}

}
