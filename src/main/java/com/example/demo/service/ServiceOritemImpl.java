package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DaoOritem;
import com.example.demo.model.Oritem;

@Service
public class ServiceOritemImpl implements ServiceOritem {

	@Autowired
	private DaoOritem daooritem;
	
	@Override
	@Transactional
	public List<Oritem> findPendientes() {
		return daooritem.findPendientes();
	}
	
}
