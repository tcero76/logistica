package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "orec",catalog = "logistica")
public class Orec {

	
    public Orec() {
	}

	public Orec(String material, Integer cantidad) {
		super();
	}
 
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idorec", unique = true, nullable = false)
    private Integer idorec;
    
    
    @Column(name = "guiadedespacho")
    @NotEmpty(message = "Se debe ingresar una guía")
    private String guiadedespacho;

    @OneToMany(mappedBy = "orec",cascade = {CascadeType.ALL})
    public Set<Oritem> oritems;
    
	public Set<Oritem> getOritems() {
		return oritems;
	}

	public void setOritems(Set<Oritem> oritems) {
		this.oritems = oritems;
	}

	public String getGuiadedespacho() {
		return guiadedespacho;
	}

	public void setGuiadedespacho(String guiadedespacho) {
		this.guiadedespacho = guiadedespacho;
	}

	public Integer getIdorec() {
		return idorec;
	}

	public void setIdorec(Integer idorec) {
		this.idorec = idorec;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Orec))
			return false;
		Orec castOther = (Orec) other;

		return ((this.getIdorec() == castOther.getIdorec()) || (this.getIdorec() != null
				&& castOther.getIdorec() != null && this.getIdorec().equals(castOther.getIdorec())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdorec() == null ? 0 : this.getIdorec().hashCode());

		return result;
	}
	
}
