package br.com.fiap.fintech.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.fintech.javabeans.Usuario;

public class UsuarioDAO {

	private Connection conexao;

	public void cadastrar(Usuario usuario) {
		PreparedStatement ps = null;

		try {

			Connection conexao = FintechDbManager.obterConexao();

			//System.out.println("Conectado!");

			ps = conexao.prepareStatement(
					"INSERT INTO t_usuario(ds_email, ds_senha, nm_completo, dt_nascimento, ds_genero) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNomeCompleto());
			Date ldata = Date.valueOf(usuario.getDataNascimento());
			ps.setDate(4, ldata);
			ps.setString(5, usuario.getGenero());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no Banco de Dados");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				//conexao.close();
			} catch (SQLException e) {
				System.err.println("O Driver JDBC não foi encontrado!");
				e.printStackTrace();
			}
		}
	}
}
