package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.bean.Recebimento;
import br.com.fiap.fintech.dao.RecebimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleRecebimentoDAO implements RecebimentoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Recebimento recebimento) throws DBException {
		PreparedStatement ps = null;

		try {

			conexao = ConnectionManager.getInstance().getConnection();

			ps = conexao.prepareStatement(
					"INSERT INTO t_recebimento(t_usuario_ds_email, cd_recebimento, vl_recebimento, dt_recebimento) VALUES (?, sq_recebimento.nextval, ?, ?)");
			ps.setString(1, "user");
			ps.setDouble(2, recebimento.getValor());
			Date ldata = Date.valueOf(recebimento.getData());
			ps.setDate(3, ldata);
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
	public void atualizar(Recebimento recebimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"UPDATE t_recebimento SET vl_recebimento = ?, dt_recebimento = ? WHERE cd_recebimento = ?");
			stmt.setDouble(1, recebimento.getValor());
			Date ldata = Date.valueOf(recebimento.getData());
			stmt.setDate(2, ldata);
			stmt.setInt(3, recebimento.getCodigo());
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
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("DELETE FROM t_recebimento WHERE cd_recebimento = ?");
			stmt.setInt(1, codigo);
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
	public Recebimento buscar(int id) {
		Recebimento recebimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT cd_recebimento, vl_recebimento, dt_recebimento FROM t_recebimento WHERE cd_recebimento = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				;
				int codigo = rs.getInt("cd_recebimento");
				double valor = rs.getDouble("vl_recebimento");
				java.sql.Date datasql = rs.getDate("dt_recebimento");
				Date data = new Date(datasql.getTime());

				recebimento = new Recebimento(usuario, codigo, valor, data);
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
		return recebimento;
	}

	@Override
	public List<Recebimento> listar() {
		List<Recebimento> lista = new ArrayList<Recebimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_recebimento");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				int codigo = rs.getInt("cd_recebimento");
				double valor = rs.getDouble("vl_recebimento");
				java.sql.Date datasql = rs.getDate("dt_recebimento");
				Date data = new Date(datasql.getTime());

				Recebimento recebimento = new Recebimento(usuario, codigo, valor, data);
				lista.add(recebimento);
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
		return lista;
	}
}
