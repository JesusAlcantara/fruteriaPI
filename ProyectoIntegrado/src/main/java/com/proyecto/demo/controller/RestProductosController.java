package com.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.demo.model.ProductoModel;
import com.proyecto.demo.service.Impl.ProductoServiceImpl;

@RestController
@RequestMapping("/api")
public class RestProductosController {

	@Autowired
	@Qualifier("productoService")
	private ProductoServiceImpl productoService;	
	
	@GetMapping("/listProductos")
	public ResponseEntity<?> listarProductos() {
		List<ProductoModel> result = productoService.listAllProductos();
		
		if(result == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result);
	}
	
	@GetMapping("/listProductos/{id}")
	public ProductoModel producto(@PathVariable long id) {
		return productoService.findProducto(id);
	}

}
