package br.univel.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.domain.Produto;

public class ProdutoModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Produto> listProdutos;

	public ProdutoModel() {

	}

	public ProdutoModel(List<Produto> produtos) {
		this.listProdutos = produtos;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return listProdutos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Produto produto = listProdutos.get(row);

		switch (col) {
		case 0:
			return produto.getId();
		case 1:
			return produto.getDescricao();
		case 2:
			return produto.getPreco();

		default:
			return "DEU RUIM!";
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "Descrição";
		case 2:
			return "Preço";

		default:
			return "DEU RUIM";
		}
	}

	
	
}
