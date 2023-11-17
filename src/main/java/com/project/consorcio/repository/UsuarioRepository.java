package com.project.consorcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.consorcio.entity.Enlace;
import com.project.consorcio.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	//JHQL
	@Query("Select u from Usuario u where u.login=?1")
	public Usuario iniciarSesion(String vLogin);
	
	/**Select 	e.*
		from tb_rol_enlace re join tb_enlace e 
        on re.idenlace = e.idenlace 
        where re.idrol = 2;**/
	@Query("Select e from RolEnlace re join re.enlace e where re.rol.descripcion=?1")//RolEnlace va primero porque contiene a la Entidad Enlace
	public List<Enlace> traerEnlacesDelUsuario(String desRol);
	
}
