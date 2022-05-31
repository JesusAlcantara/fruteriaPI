package com.proyecto.demo.model;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.entity.Usuario;

public class ProductoModel {

	private long id;
	private String nombre;
	private float precio;
	private Categoria categoria;
	private Usuario usuario;
	private Pedido pedido;
	private String foto;
	private int cantidad;
	
	public ProductoModel() {}
	
	public ProductoModel(long id, String nombre, float precio, Categoria categoria, Usuario usuario, Pedido pedido, String foto, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.usuario = usuario;
		this.pedido = pedido;
		this.foto = foto;
		this.cantidad = cantidad;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "ProductoModel [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria
				+ ", foto=" + foto + ", cantidad=" + cantidad + "]";
	}
	
}
