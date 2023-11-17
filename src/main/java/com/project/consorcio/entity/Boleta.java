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
@Table(name = "tb_boleta")
public class Boleta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_bol")
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name = "cod_cli")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_usu")
	private Usuario usuario;
	
	@Column(name = "fec_emi_bol")
	private Date fechaEmision;
	
	@Column(name = "monto_bol")
	private double monto;
	
	@OneToMany(mappedBy = "boleta")
	private List<BoletaMedicamento> listaBolestaMedicamento;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public List<BoletaMedicamento> getListaBolestaMedicamento() {
		return listaBolestaMedicamento;
	}

	public void setListaBolestaMedicamento(List<BoletaMedicamento> listaBolestaMedicamento) {
		this.listaBolestaMedicamento = listaBolestaMedicamento;
	}
	
}
