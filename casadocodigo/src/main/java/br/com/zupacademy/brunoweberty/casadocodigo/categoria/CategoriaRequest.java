package br.com.zupacademy.brunoweberty.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

	@NotBlank
	private String nome;
	
	public CategoriaRequest() {
	}

	public CategoriaRequest(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public Categoria converteEmModelo() {
		return new Categoria(this.nome);
	}
}
