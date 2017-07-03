package br.univel.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

	public static String status = "N�o conectou...";

	public static Connection getConexaoMySQL() {

		Connection connection = null; // atributo do tipo Connection

		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "trabPaulo"; // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root"; // nome de um usu�rio de seu BD
			String password = "root"; // sua senha de acesso
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {
				status = ("Conectado com sucesso!");
			} else {
				status = ("N�o foi possivel realizar conex�o");
			}

			return connection;

		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;

		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;

		}

	}

	public static String statusConection() {
		return status;
	}

	public static boolean FecharConexao() {
		try {
			ConexaoMySQL.getConexaoMySQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static Connection ReiniciarConexao() {
		FecharConexao();
		return ConexaoMySQL.getConexaoMySQL();
	}
	
}
