package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usuario;

public interface RepoUsuario extends JpaRepository<Usuario,Integer>{
	public Usuario findByNombre(String nombre);
}
