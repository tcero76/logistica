package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Almacen;
import com.example.demo.model.Inventario;
import com.example.demo.model.Material;
import com.example.demo.model.Pos;

public interface DaoInventario {
	
	public Integer findByMaterial(Material material, Pos pos);
	public Inventario findById(Integer idinventario);
	public void guardar(Inventario inventario);
	public List<Inventario> findInventario(Almacen almacen);

}
