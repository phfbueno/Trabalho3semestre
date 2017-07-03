package br.univel.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.domain.ProdutoOrcamento;

public class OrcamentoModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<ProdutoOrcamento> listProdutos;

	public OrcamentoModel() {

	}

	public OrcamentoModel(List<ProdutoOrcamento> produtos) {
		this.listProdutos = produtos;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return listProdutos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		ProdutoOrcamento produto = listProdutos.get(row);

		switch (col) {
		case 0:
			return produto.getId_produto();
		case 1:
			return produto.getDescricao();
		case 2:
			return produto.getVlrUnitario();
		case 3:
			return produto.getQtd();
		case 4:
			return produto.getSubTotal();

		default:
			return "DEU RUIM!";
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Cod Produto";
		case 1:
			return "Descrição";
		case 2:
			return "Preço";
		case 3:
			return "Quantidade";
		case 4:
			return "SubTotal";

		default:
			return "DEU RUIM";
		}
	}

	
	
}
