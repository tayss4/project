package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleInvestimentoDAO implements InvestimentoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Investimento investimento) throws DBException {
		PreparedStatement ps = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			ps = conexao.prepareStatement("INSERT INTO t_investimento(t_usuario_ds_email, cd_aplicacao, tp_investimento, vl_aplicacao, dt_aplicacao) VALUES (?, sq_investimento.nextval, ?, ?, ?)");
			ps.setString(1, "user");
			ps.setString(2, investimento.getTipoInvestimento());
			ps.setDouble(3, investimento.getValor());
			Date ldata = Date.valueOf(investimento.getData());
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
	public void atualizar(Investimento investimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement(
					"UPDATE t_investimento SET tp_investimento = ?, vl_aplicacao = ?, dt_aplicacao = ? WHERE cd_aplicacao = ?");
			stmt.setString(1, investimento.getTipoInvestimento());
			stmt.setDouble(2, investimento.getValor());
			Date ldata = Date.valueOf(investimento.getData());
			stmt.setDate(3, ldata);
			stmt.setInt(4, investimento.getCodigoAplicacao());
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
	public void remover(int codigoAplicacao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("DELETE FROM t_investimento WHERE cd_aplicacao = ?");
			stmt.setInt(1, codigoAplicacao);
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
	public Investimento buscar(int id) {
		Investimento investimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT cd_aplicacao, tp_investimento, vl_aplicacao, dt_aplicacao FROM t_recebimento WHERE cd_aplicacao = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String usuario = rs.getString("t_usuario_ds_email");
				int codigoAplicacao = rs.getInt("cd_aplicacao");
				String tipoInvestimento = rs.getString("tp_investimento");
				double valor = rs.getDouble("vl_aplicacao");
				java.sql.Date datasql = rs.getDate("dt_aplicacao");
				Date data = new Date(datasql.getTime());

				investimento = new Investimento(usuario, codigoAplicacao, tipoInvestimento, valor, data);
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
		return investimento;
	}
	
	@Override
	public List<Investimento> listar() {
		List<Investimento> lista = new ArrayList<Investimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM t_investimento");
			rs = stmt.executeQuery();

			while (rs.next()) {
				 String usuario = rs.getString("t_usuario_ds_email");
                 int codigoAplicacao = rs.getInt("cd_aplicacao");
                 String tipoInvestimento = rs.getString("tp_investimento");
                 double valor = rs.getDouble("vl_aplicacao");
                 java.sql.Date datasql = rs.getDate("dt_aplicacao");
 				 Date data = new Date(datasql.getTime());
                 
                 Investimento investimento = new Investimento(usuario, codigoAplicacao,tipoInvestimento, valor, data);
                 
				 lista.add(investimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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