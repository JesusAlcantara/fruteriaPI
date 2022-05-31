package com.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

@RequestMapping("/inicio")
@Controller
public class EmpleadoController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;

	private static final String EMPLEADOS_VIEW = "listEmpleados";
	private static final String FORMEMPLEADO_VIEW = "formEmpleado";
	private static final String CLIENTES_VIEW = "listClientes";
	
	// *** FUNCIONES ADMIN ***
	
	// Listado de todos los empleados
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listEmpleados")
	public ModelAndView listEmpleados() {
		ModelAndView mav = new ModelAndView(EMPLEADOS_VIEW);
		List<UsuarioModel> rolEmpleado = usuarioService.findUsuarioByRol("ROL_EMPLEADO");
		mav.addObject("empleados", rolEmpleado);
		return mav;
	}
	
	// Formulario para crear un nuevo empleado o modificar uno existente
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping(value = { "/listEmpleados/formEmpleado", "/listEmpleados/formEmpleado/{id}" })
	public String formEmpleado(@PathVariable(name = "id", required = false) Long id, Model model) {
		if (id == null)
			model.addAttribute("empleado", new UsuarioModel());
		else
			model.addAttribute("empleado", usuarioService.findUsuario(id));
		return FORMEMPLEADO_VIEW;
	}
	
	// Borrado de un empleado
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listEmpleados/delete/{id}")
	public String deleteEmpleado(@PathVariable long id, RedirectAttributes flash) {
		if (usuarioService.removeUsuario(id) == 0) {
			flash.addFlashAttribute("success", "Empleado eliminado correctamente.");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar al empleado.");
		
		return "redirect:/inicio/listEmpleados";
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Creación de un empleado o modificación de tal
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@PostMapping("/listEmpleados/addEmpleado")
	public String addEmpleado(@ModelAttribute("empleado") UsuarioModel usuarioModel, RedirectAttributes flash) {
		if(usuarioService.findUsuario(usuarioModel.getId()) == null) {
			try {
			usuarioModel.setRol("ROL_EMPLEADO");
			usuarioModel.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
			usuarioService.addUsuario(usuarioModel);
			flash.addFlashAttribute("success", "Empleado creado correctamente.");
			}
			catch (Exception SQLIntegrityConstraintViolationException) {
				flash.addFlashAttribute("error", "Ese correo ya esta registrado.");
				return "redirect:/inicio/listEmpleados/formEmpleado";
			}
		}
		else {
			if(usuarioModel.getPassword().equalsIgnoreCase("")) {
				usuarioModel.setPassword(usuarioService.findUsuario(usuarioModel.getId()).getPassword());
				usuarioModel.setRol("ROL_EMPLEADO");
				usuarioService.addUsuario(usuarioModel);
				flash.addFlashAttribute("success", "Empleado editado correctamente.");
			}
			else {
				usuarioModel.setRol("ROL_EMPLEADO");
				usuarioModel.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
				usuarioService.addUsuario(usuarioModel);
				flash.addFlashAttribute("success", "Empleado editado correctamente.");
			}
		}
		return "redirect:/inicio/listEmpleados";
	}
	
	// Dar de alta a un empleado
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listEmpleados/activar/{id}")
	public String activar(@PathVariable long id, RedirectAttributes flash) {

		UsuarioModel usuario=usuarioService.findUsuario(id);
		usuario.setActivo(1);
		usuarioService.addUsuario(usuario);
		flash.addFlashAttribute("success", "Empleado dado de alta correctamente.");
		return "redirect:/inicio/listEmpleados";
	}
	
	// Dar de baja a un empleado
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listEmpleados/desactivar/{id}")
	public String desactivar(@PathVariable long id, RedirectAttributes flash) {
		UsuarioModel usuario=usuarioService.findUsuario(id);
		usuario.setActivo(0);
		usuarioService.addUsuario(usuario);
		flash.addFlashAttribute("success", "Empleado dado de baja correctamente.");
		return "redirect:/inicio/listEmpleados";
	}
	
	//Listado de los clientes registrados
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/listClientes")
	public ModelAndView listClientes() {
		ModelAndView mav = new ModelAndView(CLIENTES_VIEW);
		List<UsuarioModel> rolCliente = usuarioService.findUsuarioByRol("ROL_CLIENTE");
		mav.addObject("clientes", rolCliente);
		return mav;
	}
	
	// Dar de alta a un cliente
	@GetMapping("/listClientes/activar/{id}")
	public String activarCliente(@PathVariable long id, RedirectAttributes flash) {

		UsuarioModel usuario=usuarioService.findUsuario(id);
		usuario.setActivo(1);
		usuarioService.addUsuario(usuario);
		flash.addFlashAttribute("success", "Cliente dado de alta correctamente.");
		return "redirect:/inicio/listClientes";
	}
		
	// Dar de baja a un cliente
	@GetMapping("/listClientes/desactivar/{id}")
	public String desactivarCliente(@PathVariable long id, RedirectAttributes flash) {
		UsuarioModel usuario=usuarioService.findUsuario(id);
		usuario.setActivo(0);
		usuarioService.addUsuario(usuario);
		flash.addFlashAttribute("success", "Cliente dado de baja correctamente.");
		return "redirect:/inicio/listClientes";
	}
	
	// *** FUNCIONES EMPLEADO ***
	
	
}
