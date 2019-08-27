package com.example.demo.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Orec;
import com.example.demo.model.Oritem;

@Repository
public class DaoOrImpl implements DaoOr {
	
	private EntityManager entityManager;
	
	@Autowired
	public DaoOrImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Orec> listar() {
		Session tx = entityManager.unwrap(Session.class);
		String hql = "from Orec o left join fetch o.oritems oi left join fetch oi.material";
		return (List<Orec>)tx.createQuery(hql).list();
	}

	@Override
	public Boolean guardar(Orec orec) {
		Session ss = entityManager.unwrap(Session.class);
		ss.saveOrUpdate(orec);
		return ss.getTransaction().isActive();
	}

}
