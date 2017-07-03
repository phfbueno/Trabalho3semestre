package br.univel.domain;

import java.math.BigDecimal;

public class ProdutoOrcamento {

	private int id;
	private int id_item;
	private int id_produto;
	private String descricao;
	private BigDecimal vlrUnitario;
	private int qtd;
	private BigDecimal subTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "ProdutoOrcamento [id=" + id + ", id_item=" + id_item + ", id_produto=" + id_produto + ", descricao=" + descricao + ", vlrUnitario=" + vlrUnitario + ", qtd=" + qtd + ", subTotal="
				+ subTotal + "]";
	}

	public ProdutoOrcamento(int id, int id_item, int id_produto, String descricao, BigDecimal vlrUnitario, int qtd, BigDecimal subTotal) {
		super();
		this.id = id;
		this.id_item = id_item;
		this.id_produto = id_produto;
		this.descricao = descricao;
		this.vlrUnitario = vlrUnitario;
		this.qtd = qtd;
		this.subTotal = subTotal;
	}

	public ProdutoOrcamento() {
		// TODO Auto-generated constructor stub
	}
}
