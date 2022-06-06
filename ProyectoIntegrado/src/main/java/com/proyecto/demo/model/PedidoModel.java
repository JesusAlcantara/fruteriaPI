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
	private String valoracion;
	private boolean entrega;
	
	public PedidoModel() {}
	
	public PedidoModel(long id, Usuario usuario, List<Producto> productos, Date fecha_pedido,
			String valoracion, boolean entrega) {
		this.id = id;
		this.usuario = usuario;
		this.productos = productos;
		this.fecha_pedido = fecha_pedido;
		this.valoracion = valoracion;
		this.entrega = entrega;
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

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "PedidoModel [id=" + id + ", usuario=" + usuario + ", productos=" + productos + ", fecha_pedido="
				+ fecha_pedido + "]";
	}
	
}
