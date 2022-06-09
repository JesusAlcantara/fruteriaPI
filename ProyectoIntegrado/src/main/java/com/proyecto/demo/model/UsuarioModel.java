package com.proyecto.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.demo.entity.Pedido;

public class UsuarioModel {

	private long id;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private String c_password;
	private String rol;
	private String telefono;
	private List<Pedido> pedidos = new ArrayList<>();
	private int activo;
	private String token;

	public UsuarioModel() {
	}

	public UsuarioModel(long id, String nombre, String apellidos, String email, 
			String password, String c_password, String rol, String telefono, List<Pedido> pedidos, 
			int activo, String token) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.c_password = password;
		this.rol = rol;
		this.telefono = telefono;
		this.pedidos = pedidos;
		this.activo = activo;
		this.token = token;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getC_password() {
		return c_password;
	}

	public void setC_password(String c_password) {
		this.c_password = c_password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/*
	public void incribirse(Oferta oferta) {
		getOfertas().add(oferta);
	}
	*/
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", rol=" + rol + ", telefono=" + telefono + ", token=" + token + "]";
	}
	
	
}
