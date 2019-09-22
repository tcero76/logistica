package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Inventario;
import com.example.demo.model.Oditem;
import com.example.demo.repository.RepoInventario;

public class CorteStockValidator implements ConstraintValidator<CorteStock, Oditem> {
	
	@Autowired
	private RepoInventario repoInventario;

	@Override
	public boolean isValid(Oditem value, ConstraintValidatorContext context) {
		if(value.getInventario().getIdinventario()!=null && value.getCantidad()!=null) {
			Inventario i = repoInventario.findById(value.getInventario().getIdinventario()).get();
			return value.getCantidad()<=i.getTotal();
		}
		if(value.getInventario().getIdinventario()==null) return true;
		return false;
	}

}
