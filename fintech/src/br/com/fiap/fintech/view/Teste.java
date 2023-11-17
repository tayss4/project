package br.com.fiap.fintech.view;

import java.time.LocalDate;

import br.com.fiap.fintech.bean.Gasto;
import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Recebimento;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.impl.OracleGastoDAO;
import br.com.fiap.fintech.dao.impl.OracleInvestimentoDAO;
import br.com.fiap.fintech.dao.impl.OracleObjetivoDAO;
import br.com.fiap.fintech.dao.impl.OracleRecebimentoDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;
import br.com.fiap.fintech.exception.DBException;

public class Teste {

	public static void main(String[] args) throws DBException {

		// CADASTRAR USUARIO

		OracleUsuarioDAO udao = new OracleUsuarioDAO();

		Usuario usuario = new Usuario();
		usuario.setEmail("user@gmail.com");
		usuario.setSenha("user123");
		usuario.setNomeCompleto("User");
		usuario.setDataNascimento(LocalDate.of(1983, 06, 21));
		usuario.setGenero("Masculino");

		udao.cadastrar(usuario);

		System.out.println("\nUsuário cadastrado!\n");
		System.out.println(usuario.toString());
		
		// CADASTRAR RECEBIMENTO

		OracleRecebimentoDAO rdao = new OracleRecebimentoDAO();

		Recebimento recebimento = new Recebimento();
		recebimento.setValor(3750.20);
		recebimento.setData(LocalDate.of(2023, 10, 05));

		rdao.cadastrar(recebimento);

		System.out.println("\nRecebimento registrado!\n");
		System.out.println(recebimento.toString());

		// CADASTRAR GASTO

		OracleGastoDAO gdao = new OracleGastoDAO();

		Gasto gasto = new Gasto();
		gasto.setValor(630.00);
		gasto.setData(LocalDate.of(2023, 10, 11));

		gdao.cadastrar(gasto);

		System.out.println("\nGasto registrado!\n");
		System.out.println(gasto.toString());
	

		// CADASTRAR INVESTIMENTO

		OracleInvestimentoDAO idao = new OracleInvestimentoDAO();

		Investimento investimento = new Investimento();
		investimento.setTipoInvestimento("LCA");
		investimento.setValor(12500.00);
		investimento.setData(LocalDate.of(2023, 04, 18));

		idao.cadastrar(investimento);

		System.out.println("\nInvestimento registrado!\n");
		System.out.println(investimento.toString());
		

		// CADASTRAR OBJETIVO

		OracleObjetivoDAO odao = new OracleObjetivoDAO();

		Objetivo objetivo = new Objetivo();
		objetivo.setNomeObjetivo("Carro");
		objetivo.setValor(70000.00);
		objetivo.setDataRealizacao(LocalDate.of(2026, 01, 01));

		odao.cadastrar(objetivo);

		System.out.println("\nObjetivo registrado!\n");
		System.out.println(objetivo.toString());
		
	}
}