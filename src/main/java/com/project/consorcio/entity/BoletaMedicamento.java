package com.project.consorcio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_medicamento_has_boleta")
public class BoletaMedicamento {

	@EmbeddedId
	private BoletaMedicamentoPK pk;
	
	@ManyToOne
	@JoinColumn(name = "num_bol",insertable = false,updatable = false,referencedColumnName ="num_bol")
	private Boleta boleta;
	
	@ManyToOne
	@JoinColumn(name = "cod_med",insertable = false,updatable = false,referencedColumnName ="cod_med")
	private Medicamento medicamento;
	
	@Column(name = "pre")
	private double precio;
	
	@Column(name = "can")
	private int cantidad;

	public BoletaMedicamentoPK getPk() {
		return pk;
	}

	public void setPk(BoletaMedicamentoPK pk) {
		this.pk = pk;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
