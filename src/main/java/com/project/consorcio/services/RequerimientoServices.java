package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.consorcio.entity.Requerimiento;
import com.project.consorcio.entity.RequerimientoBien;
import com.project.consorcio.entity.RequerimientoBienPK;
import com.project.consorcio.repository.RequerimientoBienRepository;
import com.project.consorcio.repository.RequerimientoRepository;

@Service
public class RequerimientoServices {

	@Autowired
	private RequerimientoRepository repoRequerimiento;
	
	@Autowired
	private RequerimientoBienRepository repoRequerimientoBien;
	
	public List<Requerimiento> findAll(){
		return repoRequerimiento.findAll();
	}
	
	@Transactional
	public void  save(Requerimiento bean) {
		try {
			repoRequerimiento.save(bean);
			for(RequerimientoBien rb:bean.getListaDetalle()) {
				RequerimientoBienPK llave = new RequerimientoBienPK();
				llave.setCod_reque(bean.getCodigo());
				llave.setCod_bien(rb.getBien().getCodigo());
				rb.setPk(llave);
				repoRequerimientoBien.save(rb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
