package br.com.zupacademy.brunoweberty.casadocodigo.livro;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/livros")
public class LivroController {
	
	@PersistenceContext
    EntityManager manager;
	
	@Autowired
	LivroRepository livroRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.converterParaLivro(manager);
		livroRepository.save(livro);
		return ResponseEntity.ok(request.toString());
	}
	
	@GetMapping
	public Page<LivroResponse> lista(@PageableDefault(sort= "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
			Page<Livro> livros = livroRepository.findAll(paginacao);
			return LivroResponse.converter(livros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroResponse> detalhar(@PathVariable Long id) {
		Optional<Livro> livro = livroRepository.findById(id);
		if (livro.isPresent()) {
			return ResponseEntity.ok(new LivroResponse(livro.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
}