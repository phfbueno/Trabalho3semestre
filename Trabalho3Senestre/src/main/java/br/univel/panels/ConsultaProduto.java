package br.univel.panels;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ProdutoDao;
import br.univel.domain.Produto;
import br.univel.model.ProdutoModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConsultaProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2262926839920064131L;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tableProduto;

	private Produto produto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ConsultaProduto frame = new ConsultaProduto(null);
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
	public ConsultaProduto(Runnable runnable) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				if (runnable != null){
					runnable.run();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultarCliente();

			}

		});
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConsultar.gridx = 2;
		gbc_btnConsultar.gridy = 0;
		contentPane.add(btnConsultar, gbc_btnConsultar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		tableProduto = new JTable();
		tableProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				produto = new Produto();

				int selectedRow = tableProduto.getSelectedRow();

				produto.setId((int) tableProduto.getValueAt(selectedRow, 0));
				produto.setDescricao((String) tableProduto.getValueAt(selectedRow, 1));
				produto.setPreco((BigDecimal) tableProduto.getValueAt(selectedRow, 2));

			}
		});
		scrollPane.setViewportView(tableProduto);

		this.setVisible(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
	}

	private void consultarCliente() {

		List<Produto> listarByNameParam = ProdutoDao.listarByDescricaoParam(txtNome.getText());

		ProdutoModel model = new ProdutoModel(listarByNameParam);

		tableProduto.setModel(model);

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto cliente) {
		this.produto = cliente;
	}

}
