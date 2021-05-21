package br.com.zupacademy.brunoweberty.casadocodigo.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    private String targetAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    
    @Override
    public void initialize(ExistsValue params) { 
        targetAttribute = params.fieldName();
        klass = params.targetClass();
    }
    
    @Override 
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from "+klass.getName()+ " where "+ targetAttribute+"=:pValue");
        query.setParameter("pValue", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();


    }
}
