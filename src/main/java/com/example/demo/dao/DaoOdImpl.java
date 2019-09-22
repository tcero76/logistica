package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Od;

@Repository
public class DaoOdImpl implements DaoOd {
	
	private EntityManager em;
	
	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Boolean guardar(Od od) {
		Session ss = em.unwrap(Session.class);
		ss.save(od);
		return ss.getTransaction().isActive();
	}

}
