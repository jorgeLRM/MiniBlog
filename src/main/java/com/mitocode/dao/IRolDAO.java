package com.mitocode.dao;

import java.util.List;

import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.model.UsuarioRol;

public interface IRolDAO extends ICRUD<Rol>{
	
	Integer asignar(Usuario us, List<UsuarioRol> roles);
	
}
