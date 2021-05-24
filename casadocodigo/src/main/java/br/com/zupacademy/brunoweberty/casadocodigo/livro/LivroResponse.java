package br.com.zupacademy.brunoweberty.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.zupacademy.brunoweberty.casadocodigo.autor.AutorRequest;
import br.com.zupacademy.brunoweberty.casadocodigo.categoria.CategoriaRequest;

public class LivroResponse {

	private Long id;
	
	private String titulo;

	private String resumo;

	private String sumario;

	private BigDecimal preco;

	private Integer numPaginas;

	private String isbn;

	private LocalDate dataPublicacao;

	private CategoriaRequest categoria;

	private AutorRequest autor;
	
	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numPaginas = livro.getNumPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
		this.categoria = new CategoriaRequest(livro.getCategoria());
		this.autor= new AutorRequest(livro.getAutor());
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public CategoriaRequest getCategoria() {
		return categoria;
	}

	public AutorRequest getAutor() {
		return autor;
	}

	public static Page<LivroResponse> converter(Page<Livro> livros) {
		return livros.map(LivroResponse::new);
	}

}
