package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DaoOditem;
import com.example.demo.model.Oditem;

@Service
public class ServiceOditemImpl implements ServiceOditem {
	
	@Autowired
	private DaoOditem daoOditem;

	@Override
	@Transactional
	public Boolean save(List<Oditem> oditems) {
		for (Oditem oditem : oditems) {
			daoOditem.guardar(oditem);
		}
		return true;
	}

}
