package br.com.zupacademy.brunoweberty.casadocodigo.autor;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/autores")
public class AutorController {
	
	@Autowired
	private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;
	
	@Autowired
	AutorRepository autorRepository;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadoAutorValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorRequest request) {
		Optional<Autor> verificaEmail = autorRepository.findByEmail(request.getEmail());
		if(verificaEmail.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		Autor autor = request.converteEmModelo();
		autorRepository.save(autor);
		return ResponseEntity.ok(autor.toString());
	}
	
}
