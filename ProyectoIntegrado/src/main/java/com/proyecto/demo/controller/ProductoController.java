package com.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.demo.entity.Categoria;
import com.proyecto.demo.model.CategoriaModel;
import com.proyecto.demo.model.ProductoModel;
import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.service.Impl.CategoriaServiceImpl;
import com.proyecto.demo.service.Impl.ProductoServiceImpl;
import com.proyecto.demo.service.Impl.UploadFileService;
import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

@RequestMapping("/inicio")
@Controller
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private ProductoServiceImpl productoService;
	
	@Autowired
	@Qualifier("categoriaService")
	private CategoriaServiceImpl categoriaService;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private UploadFileService upload;
	
	private static final String PRODUCTOS_VIEW = "listAllProductos";
	private static final String PROD_EMP_VIEW = "listProductosEmpleado";
	private static final String FORM_PROD_VIEW = "formProducto";
	
	// Listado de todos los Producto
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listAllProductos")
	public ModelAndView listAllProductos() {
		ModelAndView mav = new ModelAndView(PRODUCTOS_VIEW);
		List<ProductoModel> productos = productoService.listAllProductos();
		mav.addObject("productos", productos);
		return mav;
	}
	
	// Listar todos los Producto de ese empleado
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping("/listProductosEmpleado")
	public ModelAndView listProducto() {
		ModelAndView mav = new ModelAndView(PROD_EMP_VIEW);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String usuario= auth.getName();
		UsuarioModel usuarioMode=new UsuarioModel();
		usuarioMode= usuarioService.findUsuarioByEmail(usuario);
		List<ProductoModel> productos = productoService.findProductoByUsuario(usuarioService.transform(usuarioMode));
		mav.addObject("productos", productos);
		return mav;
	}
	
	// Formulario de producto
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping(value = { "/listProductosEmpleado/formProducto", "/listProductosEmpleado/formProducto/{id}" })
	public String formProducto(@PathVariable(name = "id", required = false) Long id, Model model) {
		List<CategoriaModel> categorias=categoriaService.listCategorias();
		if (id == null) {
			model.addAttribute("producto", new ProductoModel());
			model.addAttribute("categorias", categorias);
		}
		else {
			model.addAttribute("producto", productoService.findProducto(id));
			model.addAttribute("categorias", categorias);
		}
		return FORM_PROD_VIEW;
	}
		
	// Borrado de un producto
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping("/listProductosEmpleado/delete/{id}")
	public String deleteProducto(@PathVariable long id, RedirectAttributes flash) {
		
		ProductoModel p = new ProductoModel();
		p = productoService.findProducto(id);
		
		if(!p.getFoto().equals("default.jpg")) {
			upload.deleteImage(p.getFoto());
		}
		
		if (productoService.removeProducto(id) == 0) {
			flash.addFlashAttribute("success", "Producto eliminado correctamente.");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar el producto.");
		
		return "redirect:/inicio/listProductosEmpleado";
	}
	
	// Creaci??n de un producto o modificaci??n de tal
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@PostMapping("/listProductosEmpleado/addProducto")
	public String addProducto(@ModelAttribute("producto") ProductoModel productoModel, @RequestParam("id_categoria") long categoria, RedirectAttributes flash, @RequestParam("file") MultipartFile file) {
		
		Categoria cat=categoriaService.transform(categoriaService.findCategoria(categoria));
		productoModel.setCategoria(cat);
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String empleado= auth.getName();
		UsuarioModel usuarioMode = new UsuarioModel();
		usuarioMode = usuarioService.findUsuarioByEmail(empleado);
		productoModel.setUsuario(usuarioService.transform(usuarioMode));
				
		if(productoService.findProducto(productoModel.getId()) == null) {
			String nombreImagen = upload.saveImage(file);
			productoModel.setFoto(nombreImagen);
			if(file.isEmpty()) {
				ProductoModel p = new ProductoModel();
				p = productoService.findProducto(productoModel.getId());
				productoModel.setFoto(p.getFoto());
			}
		
			try {
				productoService.addProducto(productoModel);
				flash.addFlashAttribute("success", "Producto creado correctamente.");
			}
			catch (Exception SQLIntegrityConstraintViolationException) {
				flash.addFlashAttribute("error", "Error a la hora de registrar el producto.");
				return "redirect:/inicio/listProductosEmpleado/formProducto";
			}
		}
		else {
			if(productoModel.getFoto().equalsIgnoreCase("")) {
				productoModel.setFoto(productoService.findProducto(productoModel.getId()).getFoto());
				productoService.addProducto(productoModel);
				flash.addFlashAttribute("success", "Producto editado correctamente.");
			}
			else {
				productoModel.setFoto(productoModel.getFoto());
				productoService.addProducto(productoModel);
				flash.addFlashAttribute("success", "Producto editado correctamente.");
			}
		}	
		return "redirect:/inicio/listProductosEmpleado";
	}
}
