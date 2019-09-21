package com.example.demo.dao;

import com.example.demo.model.Usuario;

public interface DaoUsuario {
	public Usuario findByNombre(String nombre);
}
