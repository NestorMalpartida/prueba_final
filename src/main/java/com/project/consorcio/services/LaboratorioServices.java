package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Laboratorio;
import com.project.consorcio.entity.TipoMedicamento;
import com.project.consorcio.repository.LaboratorioRepository;

@Service
public class LaboratorioServices {

	@Autowired
	private LaboratorioRepository repo;
	
	public List<Laboratorio> listarTodos(){
		return repo.findAll();
	}
	
	public List<TipoMedicamento> listTipoByLaboratorio(Integer codigo){
		return repo.listTipoByLaboratorio(codigo);
	}
	
}
