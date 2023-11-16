package br.com.fiap.fintech.daos;

import br.com.fiap.fintech.javabeans.Recebimento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecebimentoDAO {

	//private Connection conexao;

	public void inserir(Recebimento recebimento) {
		PreparedStatement insertps = null;

		try {

			Connection conexao = FintechDbManager.obterConexao();

			//System.out.println("Conectado!");

			insertps = conexao.prepareStatement(
					"INSERT INTO t_recebimento(t_usuario_ds_email, cd_recebimento, vl_recebimento, dt_recebimento) VALUES (?, sq_recebimento.nextval, ?, ?)");
			insertps.setString(1, "user");
			insertps.setDouble(2, recebimento.getValor());
			Date ldata = Date.valueOf(recebimento.getData());
			insertps.setDate(3, ldata);
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
