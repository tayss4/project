package br.com.fiap.fintech.javabeans;

import java.time.LocalDate;

public class Usuario {
	private String email;
	private String senha;
	private String nomeCompleto;
	private LocalDate dataNascimento;
	private String genero;
	
	public Usuario () {};
	
	public Usuario(String email, String senha, String nomeCompleto, LocalDate dataNascimento, String genero) {
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return " Nome Completo: " + nomeCompleto + " \n Email: " + email + " \n Senha: " + senha + " \n Data de Nascimento: "
				+ dataNascimento + " \n Gênero: " + genero ;
	}
	
}
