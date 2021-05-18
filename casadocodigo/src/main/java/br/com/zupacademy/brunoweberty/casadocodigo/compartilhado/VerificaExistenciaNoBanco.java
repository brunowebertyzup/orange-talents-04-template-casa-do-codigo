package br.com.zupacademy.brunoweberty.casadocodigo.compartilhado;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = VerificaExistenciaNoBancoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)//Só funciona quando código está rodando
public @interface VerificaExistenciaNoBanco {
	String message() default "{O valor informado já existe no banco de dados!}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	Class<?> tabela();
	
	String campo();
}
