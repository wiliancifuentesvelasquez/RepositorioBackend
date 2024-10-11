package com.cifuentes.app.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "his_personas")
@Entity
public class HisPersona {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ16")
	@SequenceGenerator(sequenceName = "customerss_seq15", allocationSize = 1, name = "CUST_SEQ16")
	private Long id;
	
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "edad")
	private Integer edad;
	
	@JoinColumn(name = "persona_id")
	@ManyToOne
	private Persona persona;
	

}
