package com.proyecto.demo.model;

import java.util.Date;
import java.util.List;

import com.proyecto.demo.entity.Producto;
import com.proyecto.demo.entity.Usuario;

public class PedidoModel {

	private long id;
	private Usuario usuario;
	private List<Producto> productos;
	private Date fecha_pedido;
	
	public PedidoModel() {}
	
	public PedidoModel(long id, Usuario usuario, List<Producto> productos, Date fecha_pedido) {
		this.id = id;
		this.usuario = usuario;
		this.productos = productos;
		this.fecha_pedido = fecha_pedido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Date getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}

	@Override
	public String toString() {
		return "PedidoModel [id=" + id + ", usuario=" + usuario + ", productos=" + productos + ", fecha_pedido="
				+ fecha_pedido + "]";
	}
	
}
