package br.com.fiap.fintech.javabeans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Gasto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String usuario;
	private int codigo;
	private double valor;
	private LocalDate  data;
	
    public Gasto() {}

    public Gasto(String usuario, int codigo, double valor, Date data) {
    }
    
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public int getCodigo() {
		return codigo;
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
		return " Código: " + codigo + "\n Valor: R$" + valor + "\n Data: " + data;
	}
	
}