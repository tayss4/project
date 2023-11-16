package br.com.fiap.fintech.view;

import java.time.LocalDate;

import br.com.fiap.fintech.daos.GastoDAO;
import br.com.fiap.fintech.daos.InvestimentoDAO;
import br.com.fiap.fintech.daos.ObjetivoDAO;
import br.com.fiap.fintech.daos.RecebimentoDAO;
import br.com.fiap.fintech.daos.UsuarioDAO;
import br.com.fiap.fintech.javabeans.Gasto;
import br.com.fiap.fintech.javabeans.Investimento;
import br.com.fiap.fintech.javabeans.Objetivo;
import br.com.fiap.fintech.javabeans.Recebimento;
import br.com.fiap.fintech.javabeans.Usuario;

public class Teste {

	public static void main(String[] args) {

		// CADASTRAR USUARIO

		UsuarioDAO udao = new UsuarioDAO();

		Usuario usuario = new Usuario();
		usuario.setEmail("user@gmail.com");
		usuario.setSenha("user123");
		usuario.setNomeCompleto("User");
		usuario.setDataNascimento(LocalDate.of(1983, 06, 21));
		usuario.setGenero("Masculino");

		udao.cadastrar(usuario);

		System.out.println("\nUsuário cadastrado!\n");
		System.out.println(usuario.toString());
		// INSERT RECEBIMENTO

		RecebimentoDAO rdao = new RecebimentoDAO();

		Recebimento recebimento = new Recebimento();
		recebimento.setValor(3750.20);
		recebimento.setData(LocalDate.of(2023, 10, 05));

		rdao.inserir(recebimento);

		System.out.println("\nRecebimento registrado!\n");
		System.out.println(recebimento.toString());

		// INSERT GASTO

		GastoDAO gdao = new GastoDAO();

		Gasto gasto = new Gasto();
		gasto.setValor(630.00);
		gasto.setData(LocalDate.of(2023, 10, 11));

		gdao.inserir(gasto);

		System.out.println("\nGasto registrado!\n");
		System.out.println(gasto.toString());
	

		// INSERT INVESTIMENTO

		InvestimentoDAO idao = new InvestimentoDAO();

		Investimento investimento = new Investimento();
		investimento.setTipoInvestimento("LCA");
		investimento.setValor(12500.00);
		investimento.setData(LocalDate.of(2023, 04, 18));

		idao.inserir(investimento);

		System.out.println("\nInvestimento registrado!\n");
		System.out.println(investimento.toString());
		

		// INSERT OBJETIVO

		ObjetivoDAO odao = new ObjetivoDAO();

		Objetivo objetivo = new Objetivo();
		objetivo.setNomeObjetivo("Carro");
		objetivo.setValor(70000.00);
		objetivo.setDataRealizacao(LocalDate.of(2026, 01, 01));

		odao.inserir(objetivo);

		System.out.println("\nObjetivo registrado!\n");
		System.out.println(objetivo.toString());
		
	}
}