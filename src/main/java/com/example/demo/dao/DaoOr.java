package com.example.demo.dao;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Orec;
import com.example.demo.model.Oritem;

public interface DaoOr {

	public List<Orec> listar();
	
	public Boolean guardar(Orec orec);
}
