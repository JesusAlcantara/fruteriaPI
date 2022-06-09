package com.proyecto.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.demo.entity.Usuario;
import com.proyecto.demo.model.ProductoModel;
import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.service.Impl.ProductoServiceImpl;
import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	@Qualifier("productoService")
	private ProductoServiceImpl productoService;
	
	private static final String INICIO_VIEW = "inicio";


	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "login";
	}
	
	@GetMapping("/auth/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Usuario usuario, RedirectAttributes flash) {
		try {
			if(usuarioService.findUsuario(usuario.getId())==null) {
				if(usuario.getPassword().equals(usuario.getC_password())) {
					usuarioService.registrar(usuario);
					flash.addFlashAttribute("success", "Usuario registrado satisfactoriamente");
					return "redirect:/auth/login";
					}
				} else {
					flash.addFlashAttribute("error","Las contraseñas no coinciden.");
					return "redirect:/auth/registerForm";
				}
			}
			catch (Exception e) {
				flash.addFlashAttribute("error", "Ese correo ya esta registrado");
				return "redirect:/auth/registerForm";
			}
			return null;
		
	}
	
	@GetMapping("/inicio")
	public String inicio(RedirectAttributes flash) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String usuario= auth.getName();
		usuarioService.loadUserByUsername(usuario);
		UsuarioModel usuarioMode=new UsuarioModel();
		usuarioMode=usuarioService.findUsuarioByEmail(usuario);
		if(usuarioMode.getActivo()==1) {
			String rol=usuarioService.loadUserByUsername(usuario).getAuthorities().iterator().next().toString();
			if(rol.equalsIgnoreCase("ROL_CLIENTE")) {
				return "inicioCliente";
			}
			else if(rol.equalsIgnoreCase("ROL_ADMIN")) {
				return "inicioAdmin";
			}
			else if(rol.equalsIgnoreCase("ROL_EMPLEADO")) {
				return "inicioEmpleado";
			}
			else
				return "inicio";
		}
		else {
			flash.addFlashAttribute("danger","Espere a ser dado de alta. Gracias por su paciencia.");
			return "redirect:/auth/login";
		}
	}
	
	@GetMapping("/")
	public ModelAndView inicioCliente() {
		ModelAndView mav = new ModelAndView(INICIO_VIEW);
		List<ProductoModel> productos = productoService.listAllProductos();
		mav.addObject("productos", productos);
		return mav;
	}
	
}
