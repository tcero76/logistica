package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Oditem;

@Repository
public class DaoOditemImpl implements DaoOditem {
	
	private EntityManager em;

	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Integer guardar(Oditem oditem) {
		Session ss = em.unwrap(Session.class);
		return (Integer)ss.save(oditem);
	}

}
