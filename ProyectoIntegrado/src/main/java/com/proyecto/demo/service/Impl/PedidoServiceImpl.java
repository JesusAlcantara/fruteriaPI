package com.proyecto.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.demo.repository.PedidoRepository;
import com.proyecto.demo.service.PedidoService;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	@Qualifier("pedidoRepository")
	private PedidoRepository pedidoRepository;
}
