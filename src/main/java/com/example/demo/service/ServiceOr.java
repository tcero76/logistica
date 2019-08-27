package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Orec;
import com.example.demo.model.Oritem;

public interface ServiceOr {
	
	public List<Orec> listar();
	
	public Orec guardar(Orec orec, List<Oritem> oritems);
}
