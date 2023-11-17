package com.project.consorcio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.dto.Detalle;
import com.project.consorcio.entity.Bien;
import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.entity.RequerimientoBien;
import com.project.consorcio.entity.Usuario;
import com.project.consorcio.services.BienServices;
import com.project.consorcio.services.RequerimientoServices;
import com.project.consorcio.services.TipoBienServices;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/requerimiento")
public class RequerimientoController {
	
	@Autowired
	private BienServices servicioBien;
	
	@Autowired
	private TipoBienServices servicioTipoBien;
	
	@Autowired
	private RequerimientoServices servicioRequerimiento;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("requerimiento", servicioRequerimiento.findAll());
		model.addAttribute("tipo", servicioTipoBien.findAll());
		return "requerimiento";
	}
	
	@RequestMapping("/findBienByTipo")
	@ResponseBody
	public List<Bien> findBienByTipo( @RequestParam("codigo")int cod){
		return servicioBien.findBienByTipo(cod);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(	@RequestParam("codigo")int cod,
									@RequestParam("nombre")String nom,
									@RequestParam("cantidad")int cant,
									HttpServletRequest request){
		List<Detalle> data = null;
		if( request.getSession().getAttribute("lista")== null)
			data = new ArrayList<Detalle>();
		else
			data = (List<Detalle>) request.getSession().getAttribute("lista");
		Detalle det = new Detalle();
		det.setCodigo(cod);
		det.setNombre(nom);
		det.setCantidad(cant);
		data.add(det);
		request.getSession().setAttribute("lista", data);
		return data;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/detalle")
	@ResponseBody
	public List<Detalle> detalle(HttpServletRequest request){
		List<Detalle> data = (List<Detalle>) request.getSession().getAttribute("lista");
		return data;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/save")
	public String save(	@RequestParam("numero")String numero,
						@RequestParam("descripcion")String nombre,
						@RequestParam("fecha")String fecha,
						@RequestParam("estado")String estado,
						HttpServletRequest request,
						RedirectAttributes redirect) {
		try {
			Requerimiento data = new Requerimiento();
			data.setNumero(numero);
			data.setNombre(nombre);
			data.setFecha(LocalDate.parse(fecha));
			data.setEstado(estado);
			int usuario = (int) request.getSession().getAttribute("CODIGOUSUARIO");
			Usuario u = new Usuario();
			u.setCodigo(usuario);
			data.setUsuario(u);
			
			List<RequerimientoBien> detalle = new ArrayList<RequerimientoBien>();
			
			List<Detalle> info = (List<Detalle>) request.getSession().getAttribute("lista");
			
			for(Detalle det:info) {
				RequerimientoBien rb = new RequerimientoBien();
				
				Bien b = new Bien();
				
				b.setCodigo(det.getCodigo());
				rb.setBien(b);
				rb.setCantidad(det.getCantidad());
				
				detalle.add(rb);
			}
			
			data.setListaDetalle(detalle);
			
			servicioRequerimiento.save(data);
			
			info.clear();
			request.getSession().setAttribute("lista", info);
			
			redirect.addFlashAttribute("MENSAJE", "Requerimiento registrado!");
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("MENSAJE", "ERROR al registrar!");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "redirect:/requerimiento/lista";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public List<Detalle> remove(@RequestParam("codigo")int cod,
								HttpServletRequest request){
		List<Detalle> data = (List<Detalle>) request.getSession().getAttribute("lista");
		
		for(Detalle d:data) {
			if (d.getCodigo()==cod) {
				data.remove(d);
				break;
			}
		}
		request.getSession().setAttribute("lista", data);
		return data;
	}
}
