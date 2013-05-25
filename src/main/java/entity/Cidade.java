package entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

import br.gov.frameworkdemoiselle.annotation.Name;

@Name(value="cidades")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	private Long id;

	private String nome;

	public Cidade(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + "]";
	}

	
}
