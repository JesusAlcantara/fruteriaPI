package com.proyecto.demo.service;

import java.util.List;

import com.proyecto.demo.entity.Producto;
import com.proyecto.demo.model.ProductoModel;

public interface ProductoService {

	public List<ProductoModel> listAllProductos();
	public abstract Producto addProducto(ProductoModel productoModel);
	public abstract int removeProducto(long id);
	public abstract Producto updateProducto(ProductoModel productoModel);
	public abstract ProductoModel findProducto(long id);
	public abstract Producto modify(ProductoModel productoModel);
	public abstract Producto transform(ProductoModel productoModel);
	public abstract ProductoModel transform(Producto producto);
}
