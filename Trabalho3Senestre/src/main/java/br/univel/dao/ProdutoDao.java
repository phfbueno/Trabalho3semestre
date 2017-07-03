package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.conexao.ConexaoMySQL;
import br.univel.domain.Produto;

public class ProdutoDao {

	public static void gravar(Produto produto) {

		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("insert into produto (id, descricao, preco) values (?, ?, ?) ");
			ps.setInt(1, produto.getId());
			ps.setString(2, produto.getDescricao());
			ps.setBigDecimal(3, produto.getPreco());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Produto> listarByDescricaoParam(String descricao) {

		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		try (Connection mySql = ConexaoMySQL.getConexaoMySQL()) {

			PreparedStatement ps = mySql.prepareStatement("select * from produto where descricao like ?");
			
			ps.setString(1, "%"+descricao+"%");
			
			ResultSet executeQuery = ps.executeQuery();

			while (executeQuery.next()) {

				Produto produto = new Produto();
				
				produto.setId(executeQuery.getInt("id"));
				produto.setDescricao(executeQuery.getString("descricao"));
				produto.setPreco(executeQuery.getBigDecimal("preco"));
				
				listaProdutos.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
	}
}
