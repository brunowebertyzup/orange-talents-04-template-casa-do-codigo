package br.com.zupacademy.brunoweberty.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.brunoweberty.casadocodigo.autor.Autor;
import br.com.zupacademy.brunoweberty.casadocodigo.categoria.Categoria;
import br.com.zupacademy.brunoweberty.casadocodigo.validation.ExistsValue;
import br.com.zupacademy.brunoweberty.casadocodigo.validation.UniqueValue;

public class LivroRequest {
	@NotBlank
    @UniqueValue(fieldName = "titulo", targetClass = Livro.class, message = "titulo deve ser único")
    private String titulo;

    @NotBlank @Length(max = 500)
    private String resumo;

    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private Integer numPaginas;

    @NotBlank
    private String isbn;

    @Future @JsonFormat(pattern = "yyyy-MM-d", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsValue(fieldName = "id", targetClass = Categoria.class, message = "Categoria deve existir")
    private Long categoriaId;

    @NotNull
    @ExistsValue(fieldName = "id", targetClass = Autor.class, message = "Autor deve existir")
    private Long autorId;

    public LivroRequest(String titulo,
                        String resumo,
                        String sumario,
                        BigDecimal preco,
                        Integer numPaginas,
                        String isbn,
                        LocalDate dataPublicacao,
                        Long categoriaId,
                        Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro converterParaLivro(EntityManager manager){
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        Autor autor = manager.find(Autor.class, autorId); 

        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numPaginas,
                this.isbn,
                this.dataPublicacao,
                categoria,
                autor
                );
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }

    @Override
    public String toString() {
        return "LivroRequest{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numPaginas=" + numPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoriaId=" + categoriaId +
                ", autorId=" + autorId +
                '}';
    }
}
