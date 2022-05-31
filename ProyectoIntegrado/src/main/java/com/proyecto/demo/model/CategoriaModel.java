package com.proyecto.demo.model;

public class CategoriaModel {

	private long id;
	private String nombre;
	private String descripcion;
	
	public CategoriaModel() {}
	
	public CategoriaModel(long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "CategoriaModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
