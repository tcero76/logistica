package com.example.demo.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CorteStockValidator.class)
@Documented
public @interface CorteStock {

    String message() default "Cantidad excede haber";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
