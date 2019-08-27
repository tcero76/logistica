package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Material;

public interface RepoMaterial extends JpaRepository<Material,Integer> {

	public List<Material> findAll();
}
