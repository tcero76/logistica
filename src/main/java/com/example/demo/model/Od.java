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

@Table(name = "od")
@Entity
public class Od {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idod;
	
	@Column(name = "fechareg")
	private Date fechareg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "od")
	private Set<Oditem> oditems;
	
	public Integer getIdod() {
		return idod;
	}

	public void setIdod(Integer idod) {
		this.idod = idod;
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
		result = prime * result + ((idod == null) ? 0 : idod.hashCode());
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
		Od other = (Od) obj;
		if (idod == null) {
			if (other.idod != null)
				return false;
		} else if (!idod.equals(other.idod))
			return false;
		return true;
	} 
	
}
