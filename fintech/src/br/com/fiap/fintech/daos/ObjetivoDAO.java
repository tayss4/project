package br.com.fiap.fintech.daos;

import br.com.fiap.fintech.javabeans.Objetivo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObjetivoDAO {

	private Connection conexao;

	public void inserir(Objetivo objetivo) {
		PreparedStatement insertps = null;

		try {

			Connection conexao = FintechDbManager.obterConexao();

			//System.out.println("Conectado!");

			insertps = conexao.prepareStatement(
					"INSERT INTO t_objetivo(t_usuario_ds_email, nm_objetivo, vl_objetivo, dt_realizacao) VALUES (?, ?, ?, ?)");
			insertps.setString(1, "user");
			insertps.setString(2, objetivo.getNomeObjetivo());
			insertps.setDouble(3, objetivo.getValor());
			Date ldata = Date.valueOf(objetivo.getDataRealizacao());
			insertps.setDate(4, ldata);
			insertps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no Banco de Dados");
			e.printStackTrace();
		} finally {
			try {
				insertps.close();
				//conexao.close();
			} catch (SQLException e) {
				System.err.println("O Driver JDBC não foi encontrado!");
				e.printStackTrace();
			}
		}
	}
}