package br.com.zupacademy.brunoweberty.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.brunoweberty.casadocodigo.compartilhado.VerificaExistenciaNoBanco;

public class AutorRequest {

	@NotBlank
	@Length(min = 3)
	private String nome;
	@NotBlank
	@Email
	@VerificaExistenciaNoBanco(campo = "email", tabela = Autor.class)
	private String email;
	@NotBlank
	@Length(min = 10, max = 400)
	private String descricao;

	public AutorRequest(String nome, String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	public AutorRequest(Autor autor) {
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
	}
	
	public Autor converteEmModelo() {
		return new Autor(this.nome, this.email, this.descricao);
	}

	@Override
	public String toString() {
		return "AlunoRequest [nome=" + nome + ", email=" + email + ", descricao=" + descricao + "]";
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
