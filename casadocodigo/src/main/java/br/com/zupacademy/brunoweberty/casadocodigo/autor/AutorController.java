package br.com.zupacademy.brunoweberty.casadocodigo.autor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/autores")
public class AutorController {

	@Autowired
	AutorRepository autorRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.converteEmModelo();
		autorRepository.save(autor);
		return ResponseEntity.ok(autor.toString());
	}
	
}
