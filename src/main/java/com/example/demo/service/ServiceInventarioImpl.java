package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DaoInventario;
import com.example.demo.model.Almacen;
import com.example.demo.model.Inventario;

@Service
public class ServiceInventarioImpl implements ServiceInventario {
	
	@Autowired
	private DaoInventario daoinventario;

	@Override
	@Transactional
	public Inventario registrar(Inventario inventario) {
		Integer idinventario = daoinventario.findByMaterial(inventario.getMaterial(),inventario.getPos());
		if (idinventario!=null) {
			Inventario ultInv = daoinventario.findById(idinventario);
			inventario.setTotal(ultInv.getTotal()+inventario.getCantidad());
		} else {
			inventario.setTotal(inventario.getCantidad());
		}
		daoinventario.guardar(inventario);
		return null;
	}

	@Override
	@Transactional
	public List<Inventario> findInventario(Almacen almacen) {
		List<Inventario> inventario = daoinventario.findInventario(almacen);
		return inventario;
	}

}
