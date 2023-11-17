package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleObjetivoDAO implements ObjetivoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Objetivo objetivo) throws DBException {
		PreparedStatement ps = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			ps = conexao.prepareStatement(
					"INSERT INTO t_objetivo(t_usuario_ds_email, nm_objetivo, vl_objetivo, dt_realizacao) VALUES (?, ?, ?, ?)");
			ps.setString(1, "user");
			ps.setString(2, objetivo.getNomeObjetivo());
			ps.setDouble(3, objetivo.getValor());
			Date ldata = Date.valueOf(objetivo.getDataRealizacao());
			ps.setDate(4, ldata);
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
	public void atualizar(Objetivo objetivo) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"UPDATE t_objetivo SET nm_objetivo = ?, vl_objetivo = ?, dt_realizacao = ? WHERE nm_objetivo = ?");
			stmt.setString(1, objetivo.getNomeObjetivo());
			stmt.setDouble(2, objetivo.getValor());
			Date ldata = Date.valueOf(objetivo.getDataRealizacao());
			stmt.setDate(3, ldata);
			stmt.setString(4, objetivo.getNomeObjetivo());
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
	public void remover(String nomeObjetivo) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("DELETE FROM t_objetivo WHERE nm_objetivo = ?");
			stmt.setString(1, nomeObjetivo);
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
	public Objetivo buscar(String id) {
		Objetivo objetivo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"SELECT nm_objetivo, vl_objetivo, dt_realizacao FROM t_objetivo WHERE nm_objetivo = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				String nomeObjetivo = rs.getString("nm_objetivo");
				double valor = rs.getDouble("vl_objetivo");
				java.sql.Date datasql = rs.getDate("dt_realizacao");
				Date dataRealizacao = new Date(datasql.getTime());

				objetivo = new Objetivo(usuario, nomeObjetivo, valor, dataRealizacao);
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
		return objetivo;
	}

	@Override
	public List<Objetivo> listar() {
		List<Objetivo> lista = new ArrayList<Objetivo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT * FROM t_objetivo");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				String nomeObjetivo = rs.getString("nm_objetivo");
				double valor = rs.getDouble("vl_objetivo");
				java.sql.Date datasql = rs.getDate("dt_realizacao");
				Date dataRealizacao = new Date(datasql.getTime());

				Objetivo objetivo = new Objetivo(usuario, nomeObjetivo, valor, dataRealizacao);
				lista.add(objetivo);
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