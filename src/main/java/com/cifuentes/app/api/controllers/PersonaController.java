package com.cifuentes.app.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cifuentes.app.api.models.Persona;

import com.cifuentes.app.api.services.IService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	IService<Persona> personasIService;
	
	@PostMapping
	public Map<String, Object> guardar(@RequestBody Persona p){
		p.setEdad(p.getEdad() + 1);
		personasIService.guardar(p);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("id", p.getId());
		respuesta.put("msg", "persona guardada correctamente");
		return respuesta;
		}
	
	@GetMapping
	public List<Persona> listar(){
		return personasIService.listar();
	}
	
	@GetMapping("/obtener/{id}")
	
	public Map<String, Object> obtenerPorId(@PathVariable (name = "id") Long id){
		Optional<Persona> persona = personasIService.getById(id);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		if (persona.isPresent()) {
			Persona p = persona.get();
			respuesta.put("id", p.getId());
			respuesta.put("nombre", p.getNombre());
			respuesta.put("edad", p.getEdad());
			respuesta.put("edadFutura", p.getEdad()+1);
		}else {
			respuesta.put("msg", "Persona no encontrada");
		}
		return respuesta;
	}
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody Persona p, @PathVariable(name = "id") Long id){
		Optional<Persona> persona = personasIService.getById(id);
		Map<String, String> respuesta = new HashMap<String, String>();
		if (persona.isPresent()) {
			p.setId(id);
			personasIService.guardar(p);
			respuesta.put("msg", "registro Actualizado");
		}else {
			respuesta.put("msg", "registro no actualizado");
		}
		return respuesta;
	}
}
