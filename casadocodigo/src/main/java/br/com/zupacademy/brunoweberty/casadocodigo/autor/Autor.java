package br.com.zupacademy.brunoweberty.casadocodigo.autor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	@Column(unique = true, nullable = false)
	private String email;

	private String descricao;

	@CreationTimestamp
	private LocalDateTime criadoEm = LocalDateTime.now(ZoneOffset.UTC);

	@Deprecated
	public Autor() {
	}

	public Autor(String nome, String email, String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao + ", criadoEm="
				+ criadoEm + "]";
	}
}
