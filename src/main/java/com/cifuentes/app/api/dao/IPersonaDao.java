package com.cifuentes.app.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cifuentes.app.api.models.Persona;

public interface IPersonaDao extends JpaRepository<Persona, Long>{

}
