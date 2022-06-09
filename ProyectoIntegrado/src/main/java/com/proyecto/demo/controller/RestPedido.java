package com.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.model.PedidoModel;
import com.proyecto.demo.service.Impl.PedidoServiceImpl;

@RestController
@RequestMapping("/api")
public class RestPedido {
	
	@Autowired
	@Qualifier("pedidoService")
	private PedidoServiceImpl pedidoService;
	
	@PostMapping("/pedido")
	public Pedido nuevoPedido(@RequestBody PedidoModel pedido) {
		return pedidoService.addPedido(pedido);
	}

}
