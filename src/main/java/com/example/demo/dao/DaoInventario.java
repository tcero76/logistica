package com.example.demo.dao;

import com.example.demo.model.Inventario;
import com.example.demo.model.Material;

public interface DaoInventario {
	
	public Integer findByMaterial(Material material);
	public Inventario findById(Integer idinventario);
	public void guardar(Inventario inventario);

}
