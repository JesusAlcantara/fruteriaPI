package com.proyecto.demo.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.entity.Usuario;

@Repository("pedidoRepository")
public interface PedidoRepository extends JpaRepository<Pedido, Serializable>{

	//@Query(value = "SELECT p.id FROM pedido p, pedidos_productos pp WHERE p.usuario = ?1 AND p.id = pp.pedido_id", nativeQuery = true)
	public abstract ArrayList<Pedido> findByUsuario(Usuario usuario);
	
}
