package com.proyecto.demo.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.model.CategoriaModel;
import com.proyecto.demo.repository.CategoriaRepository;
import com.proyecto.demo.service.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;

	@Override
	public List<CategoriaModel> listCategorias() {
		return categoriaRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Categoria addCategoria(CategoriaModel categoriaModel) {
		return categoriaRepository.save(transform(categoriaModel));
	}

	@Override
	public int removeCategoria(long id) {
		categoriaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Categoria updateCategoria(CategoriaModel categoriaModel) {
		return categoriaRepository.save(transform(categoriaModel));
	}

	@Override
	public CategoriaModel findCategoria(long id) {
		if(categoriaRepository.findById(id).orElse(null)==null) {
			return null;
		}
		else
			return transform(categoriaRepository.findById(id).orElse(null));
	}

	@Override
	public Categoria modify(CategoriaModel categoriaModel) {
		return null;
	}

	@Override
	public Categoria transform(CategoriaModel categoriaModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoriaModel, Categoria.class);
	}

	@Override
	public CategoriaModel transform(Categoria categoria) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoria, CategoriaModel.class);
	}
}
