package br.com.fiap.fintech.daos;

import br.com.fiap.fintech.javabeans.Investimento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvestimentoDAO {

	//private Connection conexao;

	public void inserir(Investimento investimento) {
		PreparedStatement insertps = null;

		try {

			Connection conexao = FintechDbManager.obterConexao();

			//System.out.println("Conectado!");

			insertps = conexao.prepareStatement(
					"INSERT INTO t_investimento(t_usuario_ds_email, cd_aplicacao, tp_investimento, vl_aplicacao, dt_aplicacao) VALUES (?, sq_investimento.nextval, ?, ?, ?)");
			insertps.setString(1, "user");
			insertps.setString(2, investimento.getTipoInvestimento());
			insertps.setDouble(3, investimento.getValor());
			Date ldata = Date.valueOf(investimento.getData());
			insertps.setDate(4, ldata);
			insertps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no Banco de Dados");
			e.printStackTrace();
		} finally {
			try {
				insertps.close();
			} catch (SQLException e) {
				System.err.println("O Driver JDBC não foi encontrado!");
				e.printStackTrace();
			}
		}
	}
}