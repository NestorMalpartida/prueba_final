package com.project.consorcio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.consorcio.services.DistritoServices;
import com.project.consorcio.services.EspecialidadServices;
import com.project.consorcio.services.MedicoServices;
import com.project.consorcio.services.SedeServices;

@Controller
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoServices servicioMedico;
	@Autowired
	private SedeServices servicioSede;
	@Autowired
	private EspecialidadServices servicioEspecialidad;
	@Autowired
	private DistritoServices servicioDistrito;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("medicos", servicioMedico.listarTodos());
		model.addAttribute("sedes", servicioSede.listarTodos());
		model.addAttribute("especialidades", servicioEspecialidad.listarTodos());
		model.addAttribute("distritos", servicioDistrito.listarTodos());
		return "medico";
	}
}
