package com.proyecto.demo.service;

import java.util.List;

import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.model.PedidoModel;

public interface PedidoService {

	public List<PedidoModel> listPedidos();
	public abstract Pedido transform(PedidoModel pedidoModel);
	public abstract PedidoModel transform(Pedido pedido);
	public abstract PedidoModel findPedido(long id);
	public abstract Pedido addPedido(PedidoModel pedidoModel);
}
