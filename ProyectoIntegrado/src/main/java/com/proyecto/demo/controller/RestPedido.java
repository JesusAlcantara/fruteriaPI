package com.proyecto.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.demo.entity.Producto;
import com.proyecto.demo.model.PedidoModel;
import com.proyecto.demo.service.Impl.PedidoServiceImpl;
import com.proyecto.demo.service.Impl.ProductoServiceImpl;
import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api")
public class RestPedido {
	
	@Autowired
	@Qualifier("pedidoService")
	private PedidoServiceImpl pedidoService;
	
	@Autowired
	@Qualifier("productoService")
	private ProductoServiceImpl productoService;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;
	
	@PostMapping("/pedido")
	public PedidoModel nuevoPedido(@RequestParam String formateo, @RequestParam long id, @RequestParam Date fecha_pedido,
			@RequestParam float precio_total, @RequestParam String direccion/*,
			@RequestParam Date fecha_entrega, 
			@RequestParam int pos_domicilio*/) {
		String[] parts = formateo.split("\\.");
		com.proyecto.demo.entity.Pedido pedido = new com.proyecto.demo.entity.Pedido();
		List<Producto> productos = new ArrayList<>();
		/*
		pedido.setPrecioTotal(precio_total);
		pedido.setPos_domicilio(pos_domicilio);*/
		for (int i = 0; i < parts.length; i++) {
			com.proyecto.demo.entity.Producto p = productoService.transform(productoService.findProducto(Integer.parseInt(parts[i])));
			productos.add(p);
			System.out.println(p);
		}
		pedido.setUsuario(usuarioService.transform(usuarioService.findUsuario(id)));
		pedido.setProductos(productos);
		pedido.setPrecioTotal(precio_total);
		pedido.setDireccion(direccion);
		pedido.setFecha_pedido(fecha_pedido);
		return pedidoService.transform(pedidoService.addPedido(pedidoService.transform(pedido)));
	}
	
	@GetMapping("/verPedidos/{id}")
	public ArrayList<PedidoModel> verPedidos(@PathVariable long id) {
		return pedidoService.findByUsuario(id);
	}

}
