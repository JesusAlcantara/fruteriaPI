package com.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.demo.model.PedidoModel;
import com.proyecto.demo.service.Impl.PedidoServiceImpl;

@RequestMapping("/inicio")
@Controller
public class PedidoController {
	
	@Autowired
	@Qualifier("pedidoService")
	private PedidoServiceImpl pedidoService;
	
	private final static String PEDIDOS_VIEW = "verPedidos";
	
	// Listado de todos los Pedidos
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/verPedidos")
	public ModelAndView listAllProductos() {
		ModelAndView mav = new ModelAndView(PEDIDOS_VIEW);
		List<PedidoModel> pedidos = pedidoService.listPedidos();
		mav.addObject("pedidos", pedidos);
		return mav;
	}
	
	// Confirmar entrega de un pedido
	@PreAuthorize("hasAuthority('ROL_ADMIN')")
	@GetMapping("/verPedidos/confirmar/{id}")
	public String confirmarEntrega(@PathVariable long id, RedirectAttributes flash) {
		PedidoModel pedido=pedidoService.findPedido(id);
		pedido.setEntrega(1);
		pedidoService.addPedido(pedido);
		flash.addFlashAttribute("success", "Entrega confirmada.");
		return "redirect:/inicio/verPedidos";
	}
	
	

}
