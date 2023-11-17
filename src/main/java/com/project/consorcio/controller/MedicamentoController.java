package com.project.consorcio.controller;

import java.io.File;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.entity.TipoMedicamento;
import com.project.consorcio.services.LaboratorioServices;
import com.project.consorcio.services.MedicamentoServices;
import com.project.consorcio.services.TipoMedicamentoServices;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**Permite que la vista envie un Request accediendo a las diferentes direcciones URL's**/
@Controller
/**Permite crear direcciones URL para acceder a los controladores**/
@RequestMapping("/medicamento")
public class MedicamentoController {

	@Autowired
	private MedicamentoServices servicioMedicamento;
	@Autowired
	private TipoMedicamentoServices servicioTipoMedicamento;
	@Autowired
	private LaboratorioServices servicioLaboratorio;
	
	/**Creacion de direccion URL para mostrar la página Medicamento.html**/
	@RequestMapping("/lista")
	public String index(Model model) {
		/**Crear Atributo**/
		model.addAttribute("medicamentos", servicioMedicamento.listarTodos());
		model.addAttribute("laboratorios", servicioLaboratorio.listarTodos());
		//model.addAttribute("tipos",servicioTipoMedicamento.listarTodos());
		return "medicamento";
	}

	/**Creacion de direccion URL par registrar medicamentos**/
	@RequestMapping("/save")
	public String save(	@RequestParam("codigo")Integer cod,
						@RequestParam("nombre")String nombre,
						@RequestParam("descripcion")String descripcion, 
						@RequestParam("stock") int stock,
						@RequestParam("precio")double precio, 
						@RequestParam("fecha")String fecha,
						@RequestParam("tipo")Integer codigo,
						RedirectAttributes redirect){
		try {
			//Crear objeto Medicamento
			Medicamento med = new Medicamento();
			//Set atributos del objeto usando los parámetros
			med.setNombre(nombre);
			med.setDescripcion(descripcion);
			med.setStock(stock);
			med.setPrecio(precio);
			med.setFecha(LocalDate.parse(fecha));
			//Crear el objeto TipoMedicamento
			TipoMedicamento tp = new TipoMedicamento();
			tp.setCodigo(codigo);
			//Enviar el objeto "tp" al objeto "med"
			med.setTipo(tp);
			if (cod == 0) {
				//Ubvicar al método Registrar
				servicioMedicamento.registrar(med);
				//Mensaje Positivo
				redirect.addFlashAttribute("MENSAJE", "Medicamento registrado!!");
			}
			else {
				med.setCodigo(cod);
				servicioMedicamento.actuaizar(med);
				redirect.addFlashAttribute("MENSAJE", "Medicamento actualizado!!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/medicamento/lista";
	}
	/*Creación de dirección URL para bucar medicamento por codigo*/
	@RequestMapping("/findByID")
	/**ResponseBody -- Convierte el objeto a un JSON**/
	@ResponseBody
	public Medicamento findByID(@RequestParam("codigo")Integer codigo) {
		return servicioMedicamento.buscarPorId(codigo);
	}
	
	/*Creación de dirección URL para eliminar medicamento por código*/
	@RequestMapping("/deleteByID")
	public String deleteByID(	@RequestParam("codigo")Integer codigo,
								RedirectAttributes redirect) {
		servicioMedicamento.eliminarPorId(codigo);
		redirect.addFlashAttribute("MENSAJE", "Medicamento eliminado!!");
		return "redirect:/medicamento/lista";
	}

	/*Creacion de direccion URL para listar TipoMedicamento por 'codigo' Laboratorio*/
	@RequestMapping("/consultaTipoByLab")
	@ResponseBody
	public List<TipoMedicamento> consultaTipoByLab(	@RequestParam("codigo")Integer codigo){
		return servicioLaboratorio.listTipoByLaboratorio(codigo);
	}
	
	@RequestMapping("/reportemedicamentos")
	public void medicamentos(HttpServletResponse response) {
		try {
			//invocar al método listarTodos
			List<Medicamento> lista=servicioMedicamento.listarTodos();
			//acceder al reporte "reporteMedicamento.jrxml"
			File file=ResourceUtils.getFile("classpath:medicamento.jrxml");
			//crear objeto de la clase JasperReport y manipular el objeto file
			JasperReport jasper=JasperCompileManager.compileReport(file.getAbsolutePath());
			//origen de datos "manipular lista"
			JRBeanCollectionDataSource origen=new JRBeanCollectionDataSource(lista);
			//crear reporte
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasper,null,origen);
			//salida del reporte en formato PDF
			response.setContentType("application/pdf");
			//
			OutputStream salida=response.getOutputStream();
			//exportar a pdf
			JasperExportManager.exportReportToPdfStream(jasperPrint,salida);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
