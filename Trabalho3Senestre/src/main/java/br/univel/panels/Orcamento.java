package br.univel.panels;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.dao.OrcamentoDao;
import br.univel.domain.Cliente;
import br.univel.domain.Produto;
import br.univel.domain.ProdutoOrcamento;
import br.univel.model.OrcamentoModel;

public class Orcamento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5559002313932765309L;
	private JPanel contentPane;
	private JTextField txtProduto;
	private JTextField txtQtd;
	private JTextField txtVlrUnit;
	private JTextField txtSubTotal;
	private JTextField txtNomeCliente;
	private JTable tableOrcamento;

	private ConsultaCliente consCliente;
	private ConsultaProduto consProduto;

	private Cliente cliente;
	private Produto produto;

	private BigDecimal total = new BigDecimal("0");

	private List<ProdutoOrcamento> listProdutos = new ArrayList<ProdutoOrcamento>();
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orcamento frame = new Orcamento();
					frame.setVisible(true);
					frame.setSize(800, 600);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Orcamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 50, 177, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblCliente = new JLabel("Produto");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		contentPane.add(lblCliente, gbc_lblCliente);

		txtProduto = new JTextField();
		txtProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				// Se a tecla pressionada for f2
				if (e.getKeyCode() == KeyEvent.VK_F2) {

					if (consProduto == null) {
						consProduto = new ConsultaProduto(new Runnable() {
							public void run() {
								produtoToFields(consProduto.getProduto());
								consProduto = null;
							}
						});
					}
				}
			}
		});
		GridBagConstraints gbc_txtProduto = new GridBagConstraints();
		gbc_txtProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduto.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduto.gridx = 1;
		gbc_txtProduto.gridy = 0;
		contentPane.add(txtProduto, gbc_txtProduto);
		txtProduto.setColumns(10);

		JLabel lblQtd = new JLabel("Qtd");
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.anchor = GridBagConstraints.EAST;
		gbc_lblQtd.insets = new Insets(0, 0, 5, 5);
		gbc_lblQtd.gridx = 2;
		gbc_lblQtd.gridy = 0;
		contentPane.add(lblQtd, gbc_lblQtd);

		txtQtd = new JTextField();
		txtQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				BigDecimal multiply = produto.getPreco().multiply(new BigDecimal(txtQtd.getText()));

				txtSubTotal.setText(multiply.toString());

			}
		});

		GridBagConstraints gbc_txtQtd = new GridBagConstraints();
		gbc_txtQtd.insets = new Insets(0, 0, 5, 5);
		gbc_txtQtd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtd.gridx = 3;
		gbc_txtQtd.gridy = 0;
		contentPane.add(txtQtd, gbc_txtQtd);
		txtQtd.setColumns(10);

		JLabel lblVlrUnit = new JLabel("Vlr Unit");
		GridBagConstraints gbc_lblVlrUnit = new GridBagConstraints();
		gbc_lblVlrUnit.anchor = GridBagConstraints.EAST;
		gbc_lblVlrUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblVlrUnit.gridx = 4;
		gbc_lblVlrUnit.gridy = 0;
		contentPane.add(lblVlrUnit, gbc_lblVlrUnit);

		txtVlrUnit = new JTextField();
		txtVlrUnit.setEditable(false);
		GridBagConstraints gbc_txtVlrUnit = new GridBagConstraints();
		gbc_txtVlrUnit.insets = new Insets(0, 0, 5, 5);
		gbc_txtVlrUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVlrUnit.gridx = 5;
		gbc_txtVlrUnit.gridy = 0;
		contentPane.add(txtVlrUnit, gbc_txtVlrUnit);
		txtVlrUnit.setColumns(10);

		JLabel lblSubtotal = new JLabel("SubTotal");
		GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
		gbc_lblSubtotal.anchor = GridBagConstraints.EAST;
		gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotal.gridx = 6;
		gbc_lblSubtotal.gridy = 0;
		contentPane.add(lblSubtotal, gbc_lblSubtotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		GridBagConstraints gbc_txtSubTotal = new GridBagConstraints();
		gbc_txtSubTotal.insets = new Insets(0, 0, 5, 5);
		gbc_txtSubTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSubTotal.gridx = 7;
		gbc_txtSubTotal.gridy = 0;
		contentPane.add(txtSubTotal, gbc_txtSubTotal);
		txtSubTotal.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdicionar.gridx = 8;
		gbc_btnAdicionar.gridy = 0;
		contentPane.add(btnAdicionar, gbc_btnAdicionar);

		JLabel lblCliente_1 = new JLabel("Cliente");
		GridBagConstraints gbc_lblCliente_1 = new GridBagConstraints();
		gbc_lblCliente_1.anchor = GridBagConstraints.EAST;
		gbc_lblCliente_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente_1.gridx = 0;
		gbc_lblCliente_1.gridy = 1;
		contentPane.add(lblCliente_1, gbc_lblCliente_1);

		txtNomeCliente = new JTextField();
		txtNomeCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				// Se a tecla pressionada for f2
				if (e.getKeyCode() == KeyEvent.VK_F2) {

					if (consCliente == null) {
						consCliente = new ConsultaCliente(new Runnable() {
							public void run() {
								clienteToFields(consCliente.getCliente());
								consCliente = null;
							}
						});
					}

				}

			}
		});
		GridBagConstraints gbc_txtNomeCliente = new GridBagConstraints();
		gbc_txtNomeCliente.gridwidth = 8;
		gbc_txtNomeCliente.insets = new Insets(0, 0, 5, 0);
		gbc_txtNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeCliente.gridx = 1;
		gbc_txtNomeCliente.gridy = 1;
		contentPane.add(txtNomeCliente, gbc_txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		tableOrcamento = new JTable();
		scrollPane.setViewportView(tableOrcamento);

		JLabel lblTotal = new JLabel("TOTAL");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.gridx = 7;
		gbc_lblTotal.gridy = 3;
		contentPane.add(lblTotal, gbc_lblTotal);

		txtTotal = new JTextField();
		GridBagConstraints gbc_txtTotal = new GridBagConstraints();
		gbc_txtTotal.insets = new Insets(0, 0, 5, 0);
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.gridx = 8;
		gbc_txtTotal.gridy = 3;
		contentPane.add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);

		JButton btnSalvarEGerar = new JButton("Salvar e Gerar PDF");
		btnSalvarEGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				br.univel.domain.Orcamento orc = new br.univel.domain.Orcamento();
				
				orc.setCliente(cliente);
				orc.setProdutos(listProdutos);
				orc.setTotal(total);
				
				OrcamentoDao.gravar(orc);
				
				JOptionPane.showMessageDialog(null, "Orçamento salvo com sucesso!");
			}
		});
		GridBagConstraints gbc_btnSalvarEGerar = new GridBagConstraints();
		gbc_btnSalvarEGerar.anchor = GridBagConstraints.EAST;
		gbc_btnSalvarEGerar.gridwidth = 8;
		gbc_btnSalvarEGerar.gridx = 1;
		gbc_btnSalvarEGerar.gridy = 4;
		contentPane.add(btnSalvarEGerar, gbc_btnSalvarEGerar);
	}

	private void produtoToFields(Produto produto) {
		this.produto = produto;

		txtProduto.setText(produto.getDescricao());
		txtVlrUnit.setText(produto.getPreco().toString());
	}

	private void addItem() {
		ProdutoOrcamento prod = new ProdutoOrcamento();

		prod.setDescricao(produto.getDescricao());
		prod.setId_produto(produto.getId());
		prod.setId_item(listProdutos.size() + 1);
		prod.setQtd(Integer.parseInt(txtQtd.getText()));
		prod.setSubTotal(new BigDecimal(txtSubTotal.getText()));
		prod.setVlrUnitario(produto.getPreco());

		listProdutos.add(prod);

		total = total.add(prod.getSubTotal());

		txtTotal.setText(total.toString());

		reloadTable();
		clearFieldsProduto();
	}

	private void clienteToFields(Cliente cliente) {
		this.cliente = cliente;
		if (cliente != null){
			txtNomeCliente.setText(cliente.getNome());
		}
	}
	
	private void clearFieldsProduto() {
		txtProduto.setText("");
		txtQtd.setText("1");
		txtSubTotal.setText("");
		txtVlrUnit.setText("");
	}

	private void reloadTable() {

		OrcamentoModel model = new OrcamentoModel(listProdutos);

		tableOrcamento.setModel(model);

	}
}
