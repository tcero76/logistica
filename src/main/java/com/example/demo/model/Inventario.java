package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Table(name = "inventario")
@Entity
public class Inventario implements Serializable {
	
	public Inventario() {
		super();
	}
	
	public Inventario(Date fechareg, Usuario usuario, Material material, Oritem oritem, Double cantidad, Double total,
			Pos pos, Oditem oditem) {
		super();
		this.fechareg = fechareg;
		this.usuario = usuario;
		this.material = material;
		this.oritem = oritem;
		this.cantidad = cantidad;
		this.total = total;
		this.pos = pos;
		this.oditem = oditem;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idinventario;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmaterial")
	private Material material;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idoritem")
	private Oritem oritem;
	
	@Column(name = "cantidad")
	private Double cantidad;
	
	@Column(name = "total")
	@PositiveOrZero(message = "Sin inventario")
	private Double total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpos")
	private Pos pos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idoditem")
	private Oditem oditem;

	public Integer getIdinventario() {
		return idinventario;
	}

	public void setIdinventario(Integer idinventario) {
		this.idinventario = idinventario;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Oritem getOritem() {
		return oritem;
	}

	public void setOritem(Oritem oritem) {
		this.oritem = oritem;
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

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idinventario == null) ? 0 : idinventario.hashCode());
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
		Inventario other = (Inventario) obj;
		if (idinventario == null) {
			if (other.idinventario != null)
				return false;
		} else if (!idinventario.equals(other.idinventario))
			return false;
		return true;
	}

}
