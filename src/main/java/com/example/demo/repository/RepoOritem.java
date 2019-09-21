package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Oritem;

public interface RepoOritem extends JpaRepository<Oritem,Integer> {

}
