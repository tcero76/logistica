package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DaoUsuario;
import com.example.demo.repository.RepoUsuario;

@Service
public class ServiceUsuarioImpl implements UserDetailsService {
	
	@Autowired
	private RepoUsuario repousuario;
	
	@Autowired
	private DaoUsuario daousuario;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) daousuario.findByNombre(username);
	}

}
