package com.proyecto.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.model.PedidoModel;
import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.repository.PedidoRepository;
import com.proyecto.demo.service.PedidoService;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	@Qualifier("pedidoRepository")
	private PedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;

	@Override
	public List<PedidoModel> listPedidos() {
		return pedidoRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Pedido transform(PedidoModel pedidoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(pedidoModel, Pedido.class);
	}

	@Override
	public PedidoModel transform(Pedido pedido) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(pedido, PedidoModel.class);
	}
	
	@Override
	public PedidoModel findPedido(long id) {
		if(pedidoRepository.findById(id).orElse(null)==null) {
			return null;
		}
		else
			return transform(pedidoRepository.findById(id).orElse(null));
	}
	
	@Override
	public Pedido addPedido(PedidoModel pedidoModel) {
		return pedidoRepository.save(transform(pedidoModel));
	}
	
	public ArrayList<PedidoModel> findByUsuario(long id) {
		//UsuarioModel usuario = usuarioService.findUsuario(id);
		ArrayList <com.proyecto.demo.entity.Pedido> pedidos = pedidoRepository.findByUsuario(id);
		ModelMapper modelMapper = new ModelMapper();
		ArrayList<PedidoModel> pedidos2=new ArrayList<>();
		pedidos.forEach(pedido -> pedidos2.add(modelMapper.map(pedido, PedidoModel.class)));
		return pedidos2;
	}
}
