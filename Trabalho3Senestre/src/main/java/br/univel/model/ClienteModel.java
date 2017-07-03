package br.univel.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.domain.Cliente;

public class ClienteModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Cliente> listClientes;

	public ClienteModel() {

	}

	public ClienteModel(List<Cliente> clientes) {
		this.listClientes = clientes;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return listClientes.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Cliente cliente = listClientes.get(row);

		switch (col) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getCpf();
		case 3:
			return cliente.getTelefone();

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
			return "Nome";
		case 2:
			return "CPF";
		case 3:
			return "Telefone";
			
		default:
			return "DEU RUIM";
		}
	}

	
	
}
