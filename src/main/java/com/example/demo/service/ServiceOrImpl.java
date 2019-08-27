package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DaoOr;
import com.example.demo.model.Orec;
import com.example.demo.model.Oritem;

@Service
public class ServiceOrImpl implements ServiceOr {
	

	private DaoOr daoorec;
	
	@Autowired
	public ServiceOrImpl(DaoOr daoorec) {
		this.daoorec = daoorec;
	}

	@Override
	@Transactional
	public List<Orec> listar() {
		return daoorec.listar();
	}

	@Override
	@Transactional
	public Orec guardar(Orec orec, List<Oritem> oritems) {
		for (Oritem oritem : oritems) {
			orec.getOritems().add(oritem);
			daoorec.guardar(orec);
		}
		return orec;
	}

}
