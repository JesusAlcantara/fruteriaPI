package com.proyecto.demo.service;

import java.util.List;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.model.CategoriaModel;

public interface CategoriaService {

	public List<CategoriaModel> listCategorias();
	public abstract Categoria addCategoria(CategoriaModel categoriaModel);
	public abstract int removeCategoria(long id);
	public abstract Categoria updateCategoria(CategoriaModel categoriaModel);
	public abstract CategoriaModel findCategoria(long id);
	public abstract Categoria modify(CategoriaModel categoriaModel);
	public abstract Categoria transform(CategoriaModel categoriaModel);
	public abstract CategoriaModel transform(Categoria categoria);
}
