package br.com.fiap.fintech.javabeans;

import java.sql.Date;
import java.time.LocalDate;

public class Objetivo {
	private String usuario;
	private String nomeObjetivo;
	private double valor;
	private LocalDate dataRealizacao;

	public Objetivo() {
	}

	public Objetivo(String usuario, String nomeObjetivo, double valor, Date dataRealizacao) {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNomeObjetivo() {
		return nomeObjetivo;
	}

	public void setNomeObjetivo(String nomeObjetivo) {
		this.nomeObjetivo = nomeObjetivo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDate dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	@Override
	public String toString() {
		return " Nome: " + nomeObjetivo + "\n Valor: R$" + valor + "\n Data para Realizacao: " + dataRealizacao;
	}

}
