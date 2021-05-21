package br.com.zupacademy.brunoweberty.casadocodigo.livro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
}