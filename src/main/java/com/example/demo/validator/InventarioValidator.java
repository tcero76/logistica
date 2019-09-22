package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InventarioValidator implements ConstraintValidator<Inventario,com.example.demo.model.Inventario> {

	@Override
	public boolean isValid(com.example.demo.model.Inventario value, ConstraintValidatorContext context) {
		if(value.getIdinventario()!=null) {
			return true;
		}
		return false;
	}

}
