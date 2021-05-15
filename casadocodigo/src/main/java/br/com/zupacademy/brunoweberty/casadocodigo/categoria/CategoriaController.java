package br.com.zupacademy.brunoweberty.casadocodigo.categoria;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest request) {
		Optional<Categoria> verificaNomeDuplicado = categoriaRepository.findByNome(request.getNome());
		if(verificaNomeDuplicado.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		Categoria categoria = request.converteEmModelo();
		categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria.toString());
	}
	
}
