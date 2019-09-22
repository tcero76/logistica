package com.example.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = InventarioValidator.class)
@Documented
public @interface Inventario {
    String message() default "Se debe ingresar inventario";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
