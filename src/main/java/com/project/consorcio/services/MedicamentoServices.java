package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.repository.MedicamentoRepository;

@Service
public class MedicamentoServices {

	@Autowired
	private MedicamentoRepository repo;
	
	public void registrar(Medicamento m) {
		repo.save(m);
	}
	
	public void actuaizar(Medicamento m) {
		repo.save(m);
	}
	
	public void eliminarPorId(int cod) {
		repo.deleteById(cod);
	}
	
	public Medicamento buscarPorId(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Medicamento> listarTodos(){
		return repo.findAll();
	}
}
