package com.cifuentes.app.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifuentes.app.api.dao.IPersonaDao;
import com.cifuentes.app.api.models.Persona;


@Service
public class PersonaService implements IService<Persona>{

	@Autowired 
	IPersonaDao personaDao;
	
	@Override
	public List<Persona> listar() {
		// TODO Auto-generated method stub
		List<Persona> personas = new ArrayList<Persona>();
		personas = (List<Persona>) personaDao.findAll();
		return personas;
	}

	@Override
	public Optional<Persona> getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Persona> personas = personaDao.findById(id);
		return personas;
	}

	@Override
	public void guardar(Persona t) {
		// TODO Auto-generated method stub
		this.personaDao.save(t);
		
	}
	

}
