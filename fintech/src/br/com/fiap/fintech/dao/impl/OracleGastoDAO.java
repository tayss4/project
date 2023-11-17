package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.bean.Gasto;
import br.com.fiap.fintech.dao.GastoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleGastoDAO implements GastoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Gasto gasto) throws DBException {
		PreparedStatement ps = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			ps = conexao.prepareStatement(
					"INSERT INTO t_gasto(t_usuario_ds_email, cd_gasto, vl_gasto, dt_gasto) VALUES (?, sq_gasto.nextval, ?, ?)");
			ps.setString(1, "user");
			ps.setDouble(2, gasto.getValor());
			Date ldata = Date.valueOf(gasto.getData());
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
	public void atualizar(Gasto gasto) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("UPDATE t_gasto SET vl_gasto = ?, dt_gasto = ? WHERE cd_gasto = ?");
			stmt.setDouble(1, gasto.getValor());
			Date ldata = Date.valueOf(gasto.getData());
			stmt.setDate(2, ldata);
			stmt.setInt(3, gasto.getCodigo());
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
			
			stmt = conexao.prepareStatement("DELETE FROM t_gasto WHERE cd_gasto = ?");
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
	public Gasto buscar(int id) {
		Gasto gasto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT cd_gasto, vl_gasto, dt_gasto FROM t_gasto WHERE cd_gasto = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				;
				int codigo = rs.getInt("cd_gasto");
				double valor = rs.getDouble("vl_gasto");
				java.sql.Date datasql = rs.getDate("dt_gasto");
				Date data = new Date(datasql.getTime());

				gasto = new Gasto(usuario, codigo, valor, data);
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
		return gasto;
	}

	@Override
	public List<Gasto> listar() {
		List<Gasto> lista = new ArrayList<Gasto>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM t_gasto");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				int codigo = rs.getInt("cd_gasto");
				double valor = rs.getDouble("vl_gasto");
				java.sql.Date datasql = rs.getDate("dt_gasto");
				Date data = new Date(datasql.getTime());

				Gasto gasto = new Gasto(usuario, codigo, valor, data);
				lista.add(gasto);
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