package br.com.fiap.fintech.daos;

import java.sql.Connection;
import java.sql.DriverManager;

public class FintechDbManager {
	
	public static Connection obterConexao() {
		Connection conexao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM552324", "270705");

		} catch (Exception e) {
			System.out.println("Falha na conexão." + e.getMessage());
	    }
		return conexao;
	}
}		