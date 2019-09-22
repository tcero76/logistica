package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Almacen;
import com.example.demo.model.Inventario;

public interface ServiceInventario {
	
	public Inventario registrar(Inventario inventario);
	public List<Inventario> findInventario(Almacen almacen);

}
