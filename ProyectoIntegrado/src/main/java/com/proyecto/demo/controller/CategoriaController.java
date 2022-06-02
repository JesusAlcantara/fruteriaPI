package com.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.demo.model.CategoriaModel;
import com.proyecto.demo.service.Impl.CategoriaServiceImpl;

@RequestMapping("/inicio")
@Controller
public class CategoriaController {

	@Autowired
	@Qualifier("categoriaService")
	private CategoriaServiceImpl categoriaService;
	
	private static final String CATEGORIAS_VIEW = "listCategorias";
	private static final String FORM_CAT_VIEW = "formCategoria";
	
	// Listar todos las categorías
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping("/listCategorias")
	public ModelAndView listCategorias() {
		ModelAndView mav = new ModelAndView(CATEGORIAS_VIEW);
		List<CategoriaModel> categorias = categoriaService.listCategorias();
		mav.addObject("categorias", categorias);
		return mav;
	}
	
	// Formulario de Categoría
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping(value = { "/listCategorias/formCategoria", "/listCategorias/formCategoria/{id}" })
	public String formCategoria(@PathVariable(name = "id", required = false) Long id, Model model) {
		if (id == null) {
			model.addAttribute("categoria", new CategoriaModel());
		}
		else {
			model.addAttribute("categoria", categoriaService.findCategoria(id));
		}
		return FORM_CAT_VIEW;
	}
	
	// Creación de una categoría o modificación de tal
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@PostMapping("/listCategorias/addCategoria")
	public String addCategoria(@ModelAttribute("categoria") CategoriaModel categoriaModel, RedirectAttributes flash) {
		if(categoriaService.findCategoria(categoriaModel.getId()) == null) {
			System.out.println(categoriaModel.toString());
			try {
			categoriaService.addCategoria(categoriaModel);
			flash.addFlashAttribute("success", "Categoría creada correctamente.");
			}
			catch (Exception SQLIntegrityConstraintViolationException) {
				System.out.println("Error");
				flash.addFlashAttribute("error", "Error a la hora de registrar la categoría.");
				return "redirect:/inicio/listCategorias/formCategoria";
			}
		}
		else {
			categoriaService.addCategoria(categoriaModel);
			flash.addFlashAttribute("success", "Categoría editada correctamente.");
		}
		return "redirect:/inicio/listCategorias";
	}
	
	// Borrado de una categoría
	@PreAuthorize("hasAuthority('ROL_EMPLEADO')")
	@GetMapping("/listCategorias/delete/{id}")
	public String deleteCategoria(@PathVariable long id, RedirectAttributes flash) {
		if (categoriaService.removeCategoria(id) == 0) {
			flash.addFlashAttribute("success", "Categoría eliminada correctamente.");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar la categoría.");
		
		return "redirect:/inicio/listProductosEmpleado";
	}
}
