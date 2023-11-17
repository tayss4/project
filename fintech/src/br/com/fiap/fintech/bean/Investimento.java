package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Investimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String usuario;
	private int codigoAplicacao;
	private String tipoInvestimento;
	private double valor;
	private LocalDate data;

	public Investimento() {
		}
	
	public Investimento(String usuario, int codigoAplicacao, String tipoInvestimento, double valor, Date data) {
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCodigoAplicacao() {
		return codigoAplicacao;
	}

	public String getTipoInvestimento() {
		return tipoInvestimento;
	}

	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return " Código: " + codigoAplicacao + "\n Tipo de Investimento: " + tipoInvestimento + "\n Valor: R$" + valor + "\n Data: " + data;
	}
	
	
}