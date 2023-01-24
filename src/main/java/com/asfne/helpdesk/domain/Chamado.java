package com.asfne.helpdesk.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "chamados")
public class Chamado extends AbstractEntity<Long> {

	@Column(name = "titulo", nullable = false, unique = true, length = 60)
	private String titulo;

	@ManyToOne
	@JoinColumn(name = "id_funcionario_fk")
	private Funcionario funcionario;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
