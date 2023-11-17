package com.project.consorcio.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cli")
	private Integer codigo;
	
	@Column(name = "nom_cli")
	private String nombre;
	
	@Column(name = "pat_cli")
	private String paterno;
	
	@Column(name = "mat_cli")
	private String materno;
	
	@Column(name = "sex_cli")
	private String sexo;
	
	@Column(name = "dni_cli")
	private String dni;
	
	@Column(name = "fec_nac_cli")
	private Date fechaNacimiento;
	
	@Column(name = "cel_cli")
	private String celular;
	
	@Column(name = "dir_cli")
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "cod_dis")
	private Distrito distrito;
	
	@OneToMany(mappedBy = "cliente")
	private List<Boleta> listaBolesta;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public List<Boleta> getListaBolesta() {
		return listaBolesta;
	}

	public void setListaBolesta(List<Boleta> listaBolesta) {
		this.listaBolesta = listaBolesta;
	}
	
}
