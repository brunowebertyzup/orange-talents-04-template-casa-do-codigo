package br.com.zupacademy.brunoweberty.casadocodigo.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Deprecated
	public Categoria() {
	}
	
	@Column(unique = true, nullable = false)
	private String nome;

	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
}
