package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Inventario;
import com.example.demo.model.Oditem;
import com.example.demo.model.Usuario;
import com.example.demo.service.ServiceInventario;
import com.example.demo.service.ServiceOditem;

@Controller
@RequestMapping("/despacho")
public class DespachoController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecepcionController.class);
	
	@Autowired
	private ServiceInventario serviceInventario;
	
	@Autowired
	private ServiceOditem serviceOditem;
	
	private List<Inventario> inventarios;
	private List<Oditem> oditems;
	private Oditem oditem;
	private Usuario usuario;
	
	@GetMapping("/")
	public String inicio(Model model) {
		usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		inventarios = serviceInventario.findInventario(usuario.getAlmacen());
		oditems = new ArrayList<Oditem>();
		oditem = new Oditem();
		oditem.setInventario(new Inventario());
		model.addAttribute("inventarios", inventarios);
		model.addAttribute("oditems",oditems);
		model.addAttribute("oditem", oditem);
		model.addAttribute("fragmento", "despacho/despacho.html");
		return "layout";
	}
	
	@PostMapping("/add")
	public String add(Model model,
			@Valid @ModelAttribute("oditem") Oditem oditem, BindingResult bindingOditem) {
		if (oditem.getInventario().getIdinventario()==null) {
			bindingOditem.addError(new FieldError("inventario","inventario.idinventario","Se debe seleccionar Inventario"));;
		}
		if (bindingOditem.hasErrors()) {
			model.addAttribute("inventarios", inventarios);
			return "despacho/frmOd";
		}
		Inventario inventario = inventarios.get(inventarios.indexOf(oditem.getInventario()));
		oditem.setPos(oditems.size()+1);
		Inventario i = new Inventario(
				new Date(),
				usuario,
				inventario.getMaterial(),
				null,
				oditem.getCantidad(),
				inventario.getTotal()-oditem.getCantidad(),
				inventario.getPos(),
				oditem);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Inventario>> violations = validator.validate(i);
		for (ConstraintViolation<Inventario> violation : violations) {
		    logger.error(violation.getMessage());
			model.addAttribute("inventarios", inventarios);
			bindingOditem.addError(new FieldError("cantidad","cantidad",violation.getMessage()));
			return "despacho/frmOd";
		}
		
		inventarios.remove(inventarios.indexOf(inventario));
		oditem.setInventario(i);
		oditems.add(oditem);
		model.addAttribute("oditem", new Oditem());
		model.addAttribute("inventarios", inventarios);
		return "despacho/frmOd";
	}
	
	@GetMapping("/table")
	public String table(Model model) {
		model.addAttribute("oditems", oditems);
		return "despacho/tabla";
	}
	
	@GetMapping("/save")
	public @ResponseBody Boolean guardar() {
		serviceOditem.save(oditems);
		return true;
	}
}
