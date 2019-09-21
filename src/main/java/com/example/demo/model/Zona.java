package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "zona")
@Entity
public class Zona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idzona;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idalmacen")
	@JsonIgnore
	private Almacen almacen;
	
	@OneToMany(mappedBy = "zona")
	private Set<Nivel> niveles;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	@JsonIgnore
	private Usuario usuario;

	public Integer getIdzona() {
		return idzona;
	}

	public void setIdzona(Integer idzona) {
		this.idzona = idzona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Set<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(Set<Nivel> niveles) {
		this.niveles = niveles;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idzona == null) ? 0 : idzona.hashCode());
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
		Zona other = (Zona) obj;
		if (idzona == null) {
			if (other.idzona != null)
				return false;
		} else if (!idzona.equals(other.idzona))
			return false;
		return true;
	}
	
}
