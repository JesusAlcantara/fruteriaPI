package com.proyecto.demo.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.demo.entity.Pedido;
import com.proyecto.demo.entity.Usuario;

@Repository("pedidoRepository")
public interface PedidoRepository extends JpaRepository<Pedido, Serializable>{

	@Query(value = "SELECT * FROM usuario u, pedido p, pedidos_productos pp WHERE ?1 = p.usuario AND p.id = pp.pedido_id", nativeQuery = true)
	public abstract ArrayList<Pedido> findByUsuario(long id);
}
