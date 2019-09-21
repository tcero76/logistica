package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="rol")
@Entity
public class Rol implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrol;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "rol")
	@JsonIgnore
	private Set<Usuario> usuarios;
	
	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getAuthority() {
		return "ROLE_" + nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idrol == null) ? 0 : idrol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (idrol == null) {
			if (other.idrol != null)
				return false;
		} else if (!idrol.equals(other.idrol))
			return false;
		return true;
	}
	
}
