package com.proyecto.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.entity.Producto;
import com.proyecto.demo.entity.Usuario;
import com.proyecto.demo.model.ProductoModel;
import com.proyecto.demo.repository.ProductoRepository;
import com.proyecto.demo.service.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoModel> listAllProductos() {
		return productoRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Producto addProducto(ProductoModel productoModel) {
		return productoRepository.save(transform(productoModel));
	}

	@Override
	public int removeProducto(long id) {
		productoRepository.deleteById(id);
		return 0;
	}

	@Override
	public Producto updateProducto(ProductoModel productoModel) {
		return productoRepository.save(transform(productoModel));
	}

	@Override
	public ProductoModel findProducto(long id) {
		return transform(productoRepository.findById(id));
	}

	@Override
	public Producto modify(ProductoModel productoModel) {
		return null;
	}

	@Override
	public Producto transform(ProductoModel productoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productoModel, Producto.class);
	}

	@Override
	public ProductoModel transform(Producto producto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(producto, ProductoModel.class);
	}
	
	public ArrayList<ProductoModel> findProductoByCategoria(Categoria categoria) {
		ArrayList <com.proyecto.demo.entity.Producto> productos = productoRepository.findByCategoria(categoria);
		ModelMapper modelMapper = new ModelMapper();
		ArrayList<ProductoModel> productos2=new ArrayList<>();
		productos.forEach(producto-> productos2.add(modelMapper.map(producto, ProductoModel.class)));
		return productos2;
	}
	
	public ArrayList<ProductoModel> findProductoByUsuario(Usuario usuario) {
		ArrayList<com.proyecto.demo.entity.Producto> productos = productoRepository.findByUsuario(usuario);
		ModelMapper modelMapper = new ModelMapper();
		ArrayList<ProductoModel> productos2 = new ArrayList<>();
		productos.forEach(producto-> productos2.add(modelMapper.map(producto, ProductoModel.class)));
		return productos2;
	}
}
