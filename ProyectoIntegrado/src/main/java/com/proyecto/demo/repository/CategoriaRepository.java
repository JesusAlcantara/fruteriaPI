package com.proyecto.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.demo.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Serializable>{

	public abstract Categoria findById(long id);
}
