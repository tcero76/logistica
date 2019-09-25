package com.example.demo.model;

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
import javax.validation.constraints.NotNull;

import com.example.demo.validator.CorteStock;


@Table(name = "oditem")
@Entity
public class Oditem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idoditem;
	
	@Column(name = "pos")
	private Integer pos;
	
	@Column(name = "cantidad")
	@NotNull(message = "Se debe ingresar un valor")
	private Double cantidad;
	
	@Column(name = "fechareg")
	private Date fechareg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idod")
	private Od od;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@OneToOne(mappedBy = "oditem",cascade = CascadeType.ALL)
	@NotNull(message = "Se debe seleccionar Inventario")
	private Inventario inventario;

	public Integer getIdoditem() {
		return idoditem;
	}

	public void setIdoditem(Integer idoditem) {
		this.idoditem = idoditem;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Od getOd() {
		return od;
	}

	public void setOd(Od od) {
		this.od = od;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idoditem == null) ? 0 : idoditem.hashCode());
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
		Oditem other = (Oditem) obj;
		if (idoditem == null) {
			if (other.idoditem != null)
				return false;
		} else if (!idoditem.equals(other.idoditem))
			return false;
		return true;
	}
	
}
