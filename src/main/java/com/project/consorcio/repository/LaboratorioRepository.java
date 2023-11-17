package com.project.consorcio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.consorcio.entity.Laboratorio;
import com.project.consorcio.entity.TipoMedicamento;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>{
	
	/*JHQL -- Sentencias SQL de JPA*/
	@Query("Select tm from TipoMedicamento tm where tm.laboratorio.codigo = ?1")
	public List<TipoMedicamento> listTipoByLaboratorio(Integer codigo);

}
