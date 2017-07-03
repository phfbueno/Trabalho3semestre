package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.conexao.ConexaoMySQL;
import br.univel.domain.Cliente;

public class ClienteDao {

	public static void gravar(Cliente cliente) {

		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("insert into cliente (nome, cpf, telefone) values (?, ?, ?) ");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getTelefone());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void excluir(int id) {

		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("delete from cliente where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Cliente> listarTodosClientes() {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("select * from cliente");
			ResultSet executeQuery = ps.executeQuery();

			while (executeQuery.next()) {

				Cliente cliente = new Cliente();
				
				cliente.setId(executeQuery.getInt("id"));
				cliente.setNome(executeQuery.getString("nome"));
				cliente.setCpf(executeQuery.getString("cpf"));
				cliente.setTelefone(executeQuery.getString("telefone"));
				
				listaClientes.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaClientes;
	}
	
	public static List<Cliente> listarByNameParam(String nome) {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("select * from cliente where nome like ?");
			
			ps.setString(1, "%"+nome+"%");
			
			ResultSet executeQuery = ps.executeQuery();

			while (executeQuery.next()) {

				Cliente cliente = new Cliente();
				
				cliente.setId(executeQuery.getInt("id"));
				cliente.setNome(executeQuery.getString("nome"));
				cliente.setCpf(executeQuery.getString("cpf"));
				cliente.setTelefone(executeQuery.getString("telefone"));
				
				listaClientes.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaClientes;
	}
	
	public static List<Cliente> getCliente(int id) {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("select * from cliente where id = ?");
			
			ps.setInt(1, id);
			
			ResultSet executeQuery = ps.executeQuery();

			while (executeQuery.next()) {

				Cliente cliente = new Cliente();
				
				cliente.setId(executeQuery.getInt("id"));
				cliente.setNome(executeQuery.getString("nome"));
				cliente.setCpf(executeQuery.getString("cpf"));
				cliente.setTelefone(executeQuery.getString("telefone"));
				
				listaClientes.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaClientes;
	}

	public static void alterar(Cliente cliente) {
		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("update cliente set nome = ?, cpf = ?, telefone = ? where id = ?");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getTelefone());
			ps.setInt(4, cliente.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
