package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "usuario")
@Entity
public class Usuario  implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idusuario;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "fechareg")
	private Date fechareg;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idrol")
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Inventario> inventarios;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Oritem> oritems;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Material> materials;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idalmacen")
	private Almacen almacen;
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechareg() {
		return fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Set<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(Set<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Set<Oritem> getOritems() {
		return oritems;
	}

	public void setOritems(Set<Oritem> oritems) {
		this.oritems = oritems;
	}

	public Set<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Rol> roles = Arrays.asList(rol);
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return clave;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return activo;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return activo;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return activo;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idusuario == null) {
			if (other.idusuario != null)
				return false;
		} else if (!idusuario.equals(other.idusuario))
			return false;
		return true;
	}
	
}
