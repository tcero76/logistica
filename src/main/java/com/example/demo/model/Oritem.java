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
	private Integer cantidad;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmaterial")
	@NotNull(message = "Se debe ingresar un valor")
	private Material material;
	
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}
	
}
