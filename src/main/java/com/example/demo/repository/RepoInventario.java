package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Inventario;

public interface RepoInventario  extends JpaRepository<Inventario, Integer>{
	
	public List<Inventario> findAll();

}
