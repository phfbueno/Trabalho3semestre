package br.univel.domain;

import java.math.BigDecimal;
import java.util.List;

public class Orcamento {

	private int id;
	private Cliente cliente;
	private BigDecimal total;
	private List<ProdutoOrcamento> produtos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ProdutoOrcamento> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoOrcamento> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", cliente=" + cliente + ", total=" + total + ", produtos=" + produtos + "]";
	}

	public Orcamento(int id, Cliente cliente, BigDecimal total, List<ProdutoOrcamento> produtos) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.total = total;
		this.produtos = produtos;
	}

	public Orcamento() {
		// TODO Auto-generated constructor stub
	}

}
