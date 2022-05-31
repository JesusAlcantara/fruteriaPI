package com.proyecto.demo.service;

import java.util.List;

import com.proyecto.demo.entity.Usuario;
import com.proyecto.demo.model.UsuarioModel;

public interface UsuarioService {

	public abstract List<UsuarioModel> listAllUsuarios();
	public abstract Usuario addUsuario(UsuarioModel Usuario);
	public abstract long removeUsuario(long id);
	public abstract Usuario updateUsuario(UsuarioModel UsuarioModel);
	public abstract Usuario transform(UsuarioModel UsuarioModel);
	public abstract UsuarioModel transform(Usuario Usuario);
	public abstract UsuarioModel findUsuario(long id);
}
