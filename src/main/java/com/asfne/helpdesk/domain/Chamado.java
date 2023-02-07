package com.asfne.helpdesk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "chamados")
public class Chamado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_chamado")
	private Long id;	
	

	@NotBlank(message = "O título do chamado é obrigatório.")
	@Size(max = 60, message = "O título do chamado deve ter no máximo 60 caracteres.")
	@Column(name = "titulo", nullable = false, unique = true, length = 60)
	private String titulo;

	@OneToOne
	@JoinColumn(name = "id_funcionario")
	@NotNull(message = "O funcionário responsável pela abertura do chamado deve ser informado.")
	private Funcionario funcionario;
	
	@NotNull(message = "A data de abertura do chamado deve ser informada.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataAberturaChamado;
	
	@OneToOne
	@JoinColumn(name = "id_setor")
	@NotNull(message = "O setor associado ao chamado é obrigatório.")
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

	public Date getDataAberturaChamado() {
		return dataAberturaChamado;
	}

	public void setDataAberturaChamado(Date dataAberturaChamado) {
		this.dataAberturaChamado = dataAberturaChamado;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
