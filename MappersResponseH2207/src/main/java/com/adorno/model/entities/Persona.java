package com.adorno.model.entities;

public class Persona {

	private int id;
	private String nombre;
	private int edad;
	private String color;

	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}

	public Persona(String nombre, int edad, String color) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.color = color;
	}
	

	public Persona(int id, String nombre, int edad, String color) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.color = color;
	}

	public Persona() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + "]";
	}

}
