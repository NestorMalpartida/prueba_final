package com.project.consorcio.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class BoletaMedicamentoPK {

	private int num_bol;
	private int cod_med;
	
	@Override
	public int hashCode() {
		return Objects.hash(cod_med, num_bol);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoletaMedicamentoPK other = (BoletaMedicamentoPK) obj;
		return cod_med == other.cod_med && num_bol == other.num_bol;
	}

	public int getNum_bol() {
		return num_bol;
	}

	public void setNum_bol(int num_bol) {
		this.num_bol = num_bol;
	}

	public int getCod_med() {
		return cod_med;
	}

	public void setCod_med(int cod_med) {
		this.cod_med = cod_med;
	}
	
}
