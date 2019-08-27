package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Material;
import com.example.demo.model.Orec;
import com.example.demo.model.Oritem;
import com.example.demo.repository.RepoMaterial;
import com.example.demo.service.ServiceOr;


@Controller
@RequestMapping("/orec")
public class inicioController {

	private static final Logger logger = LoggerFactory.getLogger(inicioController.class);

	private ServiceOr serviceorec;
	
	private RepoMaterial repomaterial;

	@Autowired
	public inicioController(ServiceOr serviceorec, RepoMaterial repomaterial) {
		this.serviceorec = serviceorec;
		this.repomaterial = repomaterial;
	}

	private Oritem oritem;
	private Set<Oritem> oritems;
	private Orec orec;
	private List<Oritem> oritemsAdd;
	private List<Material> materiales;

	@GetMapping("/")
	public String init(Model model) {
		oritemsAdd = new ArrayList<Oritem>();
		orec = new Orec();
		orec.setOritems(new HashSet<Oritem>());
		oritem = new Oritem();
		materiales = repomaterial.findAll();
		model.addAttribute("materiales", materiales);
		model.addAttribute("orec", orec);
		model.addAttribute("oritem", oritem);
		model.addAttribute("oritems", oritemsAdd);
		model.addAttribute("fragmento", "recepcion/recepcion.html");
		return "layout";
	}

	@GetMapping("/table")
	public String table(Model model) {
		model.addAttribute("oritems", oritemsAdd);
		return "recepcion/tablaoritem";
	}
	
	@PostMapping("/add")
	public String add(Model model,
			@Valid @ModelAttribute(name = "orec") Orec orec, BindingResult bindingOrec,
			@Valid @ModelAttribute(name = "oritem") Oritem oritem, BindingResult bindingOritem) {
		model.addAttribute("materiales", materiales);
		if(bindingOritem.hasErrors()) {
			logger.error("Datos no válidos");
			return "recepcion/orec";
		}
		if (bindingOrec.hasErrors()) {
			logger.error("Datos no válidos");
			return "recepcion/orec";
		}
		this.orec.setGuiadedespacho(orec.getGuiadedespacho());
		this.oritem = new Oritem();
		this.oritem.setCantidad(oritem.getCantidad());
		this.oritem.setMaterial(oritem.getMaterial());
		this.oritem.setFechareg(new Date());
		this.oritem.setOrec(this.orec);
		oritemsAdd.add(this.oritem);
		model.addAttribute("orec",orec);
		model.addAttribute("oritem",new Oritem());
		return "recepcion/orec";
	}

	@PostMapping("/save")
	public @ResponseBody Boolean save(Model model) {
		serviceorec.guardar(orec, oritemsAdd);
		return true;
	}
}
