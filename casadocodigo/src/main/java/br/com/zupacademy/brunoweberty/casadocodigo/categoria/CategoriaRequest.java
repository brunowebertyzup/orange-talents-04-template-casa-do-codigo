package br.com.zupacademy.brunoweberty.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.brunoweberty.casadocodigo.compartilhado.VerificaExistenciaNoBanco;

public class CategoriaRequest {

	@NotBlank
	@VerificaExistenciaNoBanco(campo = "nome", tabela = Categoria.class)
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
