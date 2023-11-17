package com.project.consorcio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.services.UsuarioServices;

/**DEFINIR ATRIBUTOS DE TIPO SESION**/
@SessionAttributes({"ENLACES", "CODIGOUSUARIO"})
@Controller
@RequestMapping("/sesion")
public class UsuarioController {

	@Autowired
	private UsuarioServices servicioUsuario;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/intranet")
	public String intranet(Authentication auth, Model model) {
		//obtener nombre del rol del usuario que inicio sesiòn
				String nomRol=auth.getAuthorities().stream()
					      .map(GrantedAuthority::getAuthority)
					      .collect(Collectors.joining(","));
		//Obtener los enlaces del usuario segun nombre de rol
		List<Enlace> lista = servicioUsuario.enlacesDelUsuario(nomRol);
		//Obtener datos del usuario que inicio sesion
		Usuario usu = servicioUsuario.sesionDelUsuario(auth.getName());
		//Atributo de tipo sesion
		model.addAttribute("ENLACES", lista);
		model.addAttribute("CODIGOUSUARIO", usu.getCodigo());
		return "intranet";
	}
}
