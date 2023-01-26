package com.asfne.helpdesk.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



//import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "chamados")
public class Chamado extends AbstractEntity<Long> {

	@Column(name = "titulo", nullable = false, unique = true, length = 60)
	private String titulo;

	@OneToOne
	@JoinColumn(name = "id_funcionario_fk")
	private Funcionario funcionario;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAberturaChamado;
	
	@ManyToOne
	@JoinColumn(name = "id_setor_fk")
	private Setor setor;
	
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
}
