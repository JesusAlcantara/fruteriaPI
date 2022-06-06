package com.proyecto.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.service.Impl.UsuarioServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class RestLogin {

	@Autowired
	private AuthenticationManager authenticationManager;	
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usuarioService;
	
	@PostMapping("/login")
	public UsuarioModel login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(email,password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = getJWTToken(email);
		UsuarioModel usuarioMode = usuarioService.findUsuarioByEmail(email);
		usuarioMode.setToken(token);
		usuarioService.addUsuario(usuarioMode);
		return usuarioMode;
	}
	
	private String getJWTToken(String email) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROL_CLIENTE");
		
		String token = Jwts
				.builder()
				.setId("softtedJWT")
				.setSubject(email)
				.claim("authorities", 
						grantedAuthorities.stream()
							.map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, 
						secretKey.getBytes()).compact();
		return "Bearer " + token;
	}
}
