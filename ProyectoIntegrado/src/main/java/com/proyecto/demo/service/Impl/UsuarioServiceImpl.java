package com.proyecto.demo.service.Impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.demo.entity.Usuario;
import com.proyecto.demo.model.PedidoModel;
import com.proyecto.demo.model.UsuarioModel;
import com.proyecto.demo.repository.UsuarioRepository;
import com.proyecto.demo.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;


	@Override
	public Usuario transform(UsuarioModel usuarioModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuarioModel, Usuario.class);
	}

	@Override
	public UsuarioModel transform(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	@Override
	public List<UsuarioModel> listAllUsuarios() {
		return usuarioRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}
	

	@Override
	public Usuario addUsuario(UsuarioModel Usuario) {
		return usuarioRepository.save(transform(Usuario));
	}

	@Override
	public Usuario updateUsuario(UsuarioModel UsuarioModel) {
		return usuarioRepository.save(transform(UsuarioModel));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.proyecto.demo.entity.Usuario usuario = usuarioRepository.findByEmail(email);

		UserBuilder builder = null;
		if (usuario != null) {
			builder = User.withUsername(email);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRol()));
		} else
			throw new UsernameNotFoundException("Usuario no encontrado.");
		return builder.build();
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public com.proyecto.demo.entity.Usuario registrar(com.proyecto.demo.entity.Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setC_password(passwordEncoder.encode(usuario.getC_password()));;
		usuario.setRol("ROL_CLIENTE");
		return usuarioRepository.save(usuario);
	}

	@Override
	public long removeUsuario(long id) {
		usuarioRepository.deleteById(id);
		return 0;
	}

	@Override
	public UsuarioModel findUsuario(long id) {
		if(usuarioRepository.findById(id).orElse(null)==null) {
			return null;
		}
		else
			return transform(usuarioRepository.findById(id).orElse(null));
	}
	
	public UsuarioModel findUsuarioByEmail(String email) {
		com.proyecto.demo.entity.Usuario usuario = usuarioRepository.findByEmail(email);
		return transform(usuario);
	}
	
	public ArrayList<UsuarioModel> findUsuarioByRol(String rol) {
		ArrayList <com.proyecto.demo.entity.Usuario> usuarios = usuarioRepository.findByRol(rol);
		ModelMapper modelMapper = new ModelMapper();
		ArrayList<UsuarioModel> usuarios2=new ArrayList<>();
		usuarios.forEach(usuario-> usuarios2.add(modelMapper.map(usuario, UsuarioModel.class)));
		return usuarios2;
	}
	
	public UsuarioModel findUsuarioByEmailAndPassword(String email, String password) {
		com.proyecto.demo.entity.Usuario usuario = usuarioRepository.findByEmail(email);
		String contra = usuario.getPassword();
		System.out.println(contra);
		System.out.println(password);
		if(passwordEncoder.matches(password, contra)) {
			System.out.println("Correcto");
			return transform(usuario);
		} else {
			System.out.println("Error");
			return null;
		}
	}
	
	/*
	
	public ArrayList<UsuarioModel> findUsuarioByOferta(long oferta) {
		ArrayList <com.proyecto.demo.entity.Usuario> usuarios = usuarioRepository.findUsuariosOfertas(oferta);
		ModelMapper modelMapper = new ModelMapper();
		ArrayList<UsuarioModel> usuarios2=new ArrayList<>();
		usuarios.forEach(usuario-> usuarios2.add(modelMapper.map(usuario, UsuarioModel.class)));
		return usuarios2;
	}
	
	*/

}
