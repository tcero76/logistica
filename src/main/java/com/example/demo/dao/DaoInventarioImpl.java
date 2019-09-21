package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Inventario;
import com.example.demo.model.Material;

@Repository
public class DaoInventarioImpl implements DaoInventario {
	
	private EntityManager em;
	
	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Integer findByMaterial(Material material) {
		Session ss = em.unwrap(Session.class);
		String hql = "select max(idinventario) from Inventario i where i.material.idmaterial = :idmaterial";
		return (Integer)ss
				.createQuery(hql)
				.setParameter("idmaterial", material.getIdmaterial())
				.uniqueResult();
	}

	@Override
	public Inventario findById(Integer idinventario) {
		Session ss = em.unwrap(Session.class);
		return (Inventario)ss.load(Inventario.class,idinventario);
	}
	
	@Override
	public void guardar(Inventario inventario) {
		Session ss = em.unwrap(Session.class);
		ss.save(inventario);
	}

}
