package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;

@Repository
public class DaoUsuarioImpl implements DaoUsuario {
	
	private EntityManager em;
	
	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Usuario findByNombre(String nombre) {
		Session ss = em.unwrap(Session.class);
		String hql = "from Usuario u left join fetch u.almacen a left join fetch a.zonas z left join fetch z.niveles n left join fetch n.poses p where u.nombre = :nombre";
		return (Usuario)ss.createQuery(hql).setParameter("nombre", nombre).uniqueResult();
	}

}
