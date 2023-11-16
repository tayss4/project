package br.com.fiap.fintech.daos;

import br.com.fiap.fintech.javabeans.Gasto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GastoDAO {

	//private Connection conexao;

	public void inserir(Gasto gasto) {
		PreparedStatement insertps = null;

		try {

			Connection conexao = FintechDbManager.obterConexao();

			//System.out.println("Conectado!");

			insertps = conexao.prepareStatement(
					"INSERT INTO t_gasto(t_usuario_ds_email, cd_gasto, vl_gasto, dt_gasto) VALUES (?, sq_gasto.nextval, ?, ?)");
			insertps.setString(1, "user");
			insertps.setDouble(2, gasto.getValor());
			Date ldata = Date.valueOf(gasto.getData());
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