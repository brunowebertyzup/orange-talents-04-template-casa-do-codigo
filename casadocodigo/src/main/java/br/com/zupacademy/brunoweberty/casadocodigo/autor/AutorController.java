package br.com.zupacademy.brunoweberty.casadocodigo.autor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {
	
	@Autowired
	AutorRepository autorRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorRequest request, UriComponentsBuilder uriBuilder) {
		Autor autor = request.converteEmModelo();
		autorRepository.save(autor);
		return ResponseEntity.ok(autor.toString());
	}
	
}
