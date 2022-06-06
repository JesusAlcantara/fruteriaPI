package com.proyecto.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido", orphanRemoval = true)
	private List<Producto> productos = new ArrayList<>();
	
	private Date fecha_pedido;
	
	private String valoracion;
	
	private boolean entrega;
	
	public Pedido() {}

	public Pedido(long id, Usuario usuario, List<Producto> productos, Date fecha_pedido,
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
	
}
