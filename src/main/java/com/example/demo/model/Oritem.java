package com.example.demo.model;

import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "oritem")
public class Oritem {
	
	public Oritem() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idoritem;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idorec")
	private Orec orec;
	
	@Column(name= "cantidad")
	@Max(value = 500,message = "La máxima cantidad es 500")
	private Double cantidad;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@Column(name = "pos")
	private Integer pos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmaterial")
	@NotNull(message = "Se debe ingresar un valor")
	private Material material;
	
	@OneToMany(mappedBy = "oritem")
	private Set<Inventario> inventarios;
	
	@Column(name = "estado")
	private String estado;
	
	public Integer getIdoritem() {
		return idoritem;
	}

	public void setIdoritem(Integer idoritem) {
		this.idoritem = idoritem;
	}

	public Orec getOrec() {
		return orec;
	}

	public void setOrec(Orec orec) {
		this.orec = orec;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Set<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(Set<Inventario> inventarios) {
		this.inventarios = inventarios;
	}


	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idoritem == null) ? 0 : idoritem.hashCode());
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
		Oritem other = (Oritem) obj;
		if (idoritem == null) {
			if (other.idoritem != null)
				return false;
		} else if (!idoritem.equals(other.idoritem))
			return false;
		return true;
	}
	
}
