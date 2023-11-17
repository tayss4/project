package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Usuario usuario) throws DBException {
		PreparedStatement ps = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
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
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar");
		} finally {
			try {
				ps.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"UPDATE t_usuario SET ds_email = ?, ds_senha = ?, nm_completo = ?, dt_nascimento = ?, ds_genero = ? WHERE ds_email = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getNomeCompleto());
			Date ldata = Date.valueOf(usuario.getDataNascimento());
			stmt.setDate(4, ldata);
			stmt.setString(5, usuario.getGenero());
			stmt.setString(6, usuario.getEmail());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void remover(String email) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("DELETE FROM t_usuario WHERE ds_email = ?");
			stmt.setString(1, email);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Usuario buscar(String id) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_usuario WHERE ds_email = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String email = rs.getString("ds_email");
				String senha = rs.getString("ds_senha");
				String nomeCompleto = rs.getString("nm_completo");
				java.sql.Date datasql = rs.getDate("dt_nascimento");
				Date dataNascimento = new Date(datasql.getTime());
				String genero = rs.getString("ds_genero");

				usuario = new Usuario(email, senha, nomeCompleto, dataNascimento, genero);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

	
}
