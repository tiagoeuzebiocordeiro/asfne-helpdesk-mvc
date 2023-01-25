package com.asfne.helpdesk.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "chamados")
public class Chamado extends AbstractEntity<Long> {

	@Column(name = "titulo", nullable = false, unique = true, length = 60)
	private String titulo;

	@ManyToOne
	@JoinColumn(name = "id_funcionario_fk")
	private Funcionario funcionario;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAberturaChamado;
	
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

	public LocalDate getDataAberturaChamado() {
		return dataAberturaChamado;
	}

	public void setDataAberturaChamado(LocalDate dataAberturaChamado) {
		this.dataAberturaChamado = dataAberturaChamado;
	}
	
	
	
}
