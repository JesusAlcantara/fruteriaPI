package com.proyecto.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	@ManyToMany
	private List<Producto> productos = new ArrayList<>();
	
	@JsonFormat(pattern = "ddMMyyyy")
	private Date fecha_pedido;
	
	@JsonFormat(pattern = "ddMMyyyy")
	private Date fecha_entrega;
	
	private String direccion;
	
	private int pos_domicilio;
	
	private String valoracion;
	
	private float precio_total;
	
	private int entrega;
	
	public Pedido() {}

	public Pedido(long id, Usuario usuario, List<Producto> productos, Date fecha_pedido,
			Date fecha_entrega, String valoracion, String direccion, int pos_domicilio, float precio_total, int entrega) {
		this.id = id;
		this.usuario = usuario;
		this.productos = productos;
		this.fecha_pedido = fecha_pedido;
		this.fecha_entrega = fecha_entrega;
		this.valoracion = valoracion;
		this.direccion = direccion;
		this.pos_domicilio = pos_domicilio;
		this.precio_total = precio_total;
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

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPos_domicilio() {
		return pos_domicilio;
	}

	public void setPos_domicilio(int pos_domicilio) {
		this.pos_domicilio = pos_domicilio;
	}

	public float getPrecioTotal() {
		return precio_total;
	}

	public void setPrecioTotal(float precio_total) {
		this.precio_total = precio_total;
	}

	public int getEntrega() {
		return entrega;
	}

	public void setEntrega(int entrega) {
		this.entrega = entrega;
	}
	
}
