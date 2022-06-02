package com.proyecto.demo.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.entity.Producto;
import com.proyecto.demo.entity.Usuario;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Serializable>{

	public abstract ArrayList<Producto> findByCategoria(Categoria categoria);
	public abstract ArrayList<Producto> findByUsuario(Usuario usuario);
	
}
