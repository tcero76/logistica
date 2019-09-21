package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Oritem;

@Repository
public class DaoOritemImpl implements DaoOritem {
	
	private EntityManager em;

	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Oritem> findPendientes() {
		Session ss = em.unwrap(Session.class);
		String hql = "from Oritem oi left join fetch oi.orec o left join fetch oi.material m where oi.estado='recepcionado'";
		return (List<Oritem>) ss.createQuery(hql).list();
	}

}
