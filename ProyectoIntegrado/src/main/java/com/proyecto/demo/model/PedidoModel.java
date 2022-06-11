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
	private Date fecha_entrega;
	private String valoracion;
	private String direccion;
	private int pos_domicilio;
	private float precioTotal;
	private int entrega;
	
	public PedidoModel() {}
	
	public PedidoModel(long id, Usuario usuario, List<Producto> productos, Date fecha_pedido,
			Date fecha_entrega, String valoracion, String direccion, int pos_domicilio, float precioTotal, int entrega) {
		this.id = id;
		this.usuario = usuario;
		this.productos = productos;
		this.fecha_pedido = fecha_pedido;
		this.fecha_entrega = fecha_entrega;
		this.valoracion = valoracion;
		this.direccion = direccion;
		this.pos_domicilio = pos_domicilio;		
		this.precioTotal = precioTotal;
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
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getEntrega() {
		return entrega;
	}

	public void setEntrega(int entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "PedidoModel [id=" + id + ", usuario=" + usuario + ", productos=" + productos + ", fecha_pedido="
				+ fecha_pedido + "]";
	}
	
}
