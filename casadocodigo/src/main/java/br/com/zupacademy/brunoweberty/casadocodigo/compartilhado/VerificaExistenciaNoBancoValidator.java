package br.com.zupacademy.brunoweberty.casadocodigo.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class VerificaExistenciaNoBancoValidator 
implements ConstraintValidator<VerificaExistenciaNoBanco, Object>{
	
	private String campoASerPesquisado;
	private Class<?> tabela;
	@PersistenceContext private EntityManager entityManager;
	
	@Override
	public void initialize(VerificaExistenciaNoBanco constraintAnnotation) {
		this.tabela = constraintAnnotation.tabela();
		this.campoASerPesquisado = constraintAnnotation.campo();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = entityManager.createQuery("select"
				+ " 1 from " + tabela.getName() + " t where " + campoASerPesquisado + " =:valor")
				.setParameter("valor", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um "+tabela+ "com o atributo "+campoASerPesquisado+" = "+value);
		return list.isEmpty();
	}

}
