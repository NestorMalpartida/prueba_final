package com.project.consorcio.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.consorcio.entity.Usuario;
import com.project.consorcio.services.UsuarioServices;

public class UserServices implements UserDetailsService{

	@Autowired
	private UsuarioServices servicioUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/**Declaracion**/
		UserDetails bean = null;
		/**Validar inicio de sesion**/
		Usuario u = servicioUsuario.sesionDelUsuario(username);
		/**Rol**/
		Set<GrantedAuthority> rol = new HashSet<GrantedAuthority>();
		/**Adicionar**/
		rol.add(new SimpleGrantedAuthority(u.getRol().getDescripcion()));
		/**Crear objeto bean**/
		bean = new User(username, u.getClave(), rol);
		return bean;
	}

}
