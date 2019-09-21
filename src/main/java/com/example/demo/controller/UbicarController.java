package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Inventario;
import com.example.demo.model.Nivel;
import com.example.demo.model.Oritem;
import com.example.demo.model.Pos;
import com.example.demo.model.Usuario;
import com.example.demo.model.Zona;
import com.example.demo.repository.RepoOritem;
import com.example.demo.service.ServiceInventario;
import com.example.demo.service.ServiceOritem;

@Controller
@RequestMapping("/ubicar")
public class UbicarController {
	
	private Usuario usuario;
	
	@Autowired
	private ServiceOritem serviceOritem;

	@Autowired
	private ServiceInventario serviceinventario;
	
	@Autowired
	private RepoOritem repooritem;
	
	private List<Oritem> oritems;
	private Oritem oritem;
	private Zona zona;
	private Nivel nivel;
	private Pos pos;
	private Set<Zona> zonas;

	@GetMapping("/")
	public String listarOr(Model model) {
		usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		zonas = (Set<Zona>) usuario.getAlmacen().getZonas();
		pos = new Pos();
		zona = new Zona();
		nivel = new Nivel();
		oritems = serviceOritem.findPendientes();
		model.addAttribute("oritems", oritems);
		model.addAttribute("zona", zona);
		model.addAttribute("zonas", zonas);
		model.addAttribute("nivel", nivel);
		model.addAttribute("pos", pos);
		model.addAttribute("fragmento", "ubicacion/ubicacion.html");
		return "layout";
	}
	
	@PostMapping("/zona")
	public String zona(Model model,
			@Valid @ModelAttribute(name = "idzona") Zona zona, BindingResult bindingZona){
		if (bindingZona.hasErrors()) {
			return null;
		}
		this.zona = zonas.stream()
		.filter(item -> item.equals(zona)).findFirst().get();
		model.addAttribute("nivel", nivel);
		model.addAttribute("niveles", this.zona.getNiveles());
		return "ubicacion/nivel";
	}
	
	@PostMapping("/nivel")
	public String posicion(Model model,
			@Valid @ModelAttribute(name = "idnivel") Nivel nivel, BindingResult bindingNivel) {
		if(bindingNivel.hasErrors()) {
			return null;
		}
		this.nivel = zona.getNiveles().stream().filter(item -> item.equals(nivel)).findFirst().get();
		pos = new Pos();
		model.addAttribute("poses", this.nivel.getPoses());
		model.addAttribute("pos", pos);
		return "ubicacion/pos";
	}
	
	@PostMapping("/oritem")
	public @ResponseBody Boolean setOritem(@ModelAttribute(name = "idoritem") Oritem oritem) {
		this.oritem = oritems.stream().filter(item -> item.equals(oritem)).findFirst().get();
		return true;
	}
	
	@PostMapping("/guardar")
	public String guardar(
			@Valid @ModelAttribute(name = "pos") Pos pos, BindingResult bindingPosicion, Model model) {
		model.addAttribute("poses", this.nivel.getPoses());
		model.addAttribute("pos", pos);
		if (bindingPosicion.hasErrors()) {
			return "ubicacion/pos :: pos";
		}
		Inventario inventario = new Inventario();
		inventario.setOritem(this.oritem);
		inventario.setPos(this.nivel.getPoses().stream().filter(item -> item.equals(pos)).findFirst().get());
		inventario.setFechareg(new Date());
		inventario.setUsuario(usuario);
		inventario.setCantidad(oritem.getCantidad());
		inventario.setMaterial(oritem.getMaterial());
		serviceinventario.registrar(inventario);
		this.oritem.setEstado("ubicado");
		repooritem.save(this.oritem);
		return "ubicacion/pos";
	}
}
