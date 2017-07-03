package br.univel.panels;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ClienteDao;
import br.univel.domain.Cliente;

public class TelaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970295835212555942L;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtID;

	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
					frame.setSize(450, 180);
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
	public TelaCliente() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 170);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 117, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);

		txtID = new JTextField();
		txtID.setEditable(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.gridx = 1;
		gbc_txtID.gridy = 0;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			private ConsultaCliente cons;

			@Override
			public void keyPressed(KeyEvent e) {

				// Se a tecla pressionada for f2
				if (e.getKeyCode() == KeyEvent.VK_F2) {

					if (cons == null) {
						cons = new ConsultaCliente(new Runnable() {
							public void run() {
								clienteToFields(cons.getCliente());
								cons = null;
							}
						});
					}

				}
			}

		});
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 6;
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpf.gridx = 0;
		gbc_lblCpf.gridy = 2;
		contentPane.add(lblCpf, gbc_lblCpf);

		txtCPF = new JTextField();
		GridBagConstraints gbc_txtCPF = new GridBagConstraints();
		gbc_txtCPF.gridwidth = 4;
		gbc_txtCPF.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCPF.insets = new Insets(0, 0, 5, 5);
		gbc_txtCPF.gridx = 1;
		gbc_txtCPF.gridy = 2;
		contentPane.add(txtCPF, gbc_txtCPF);
		txtCPF.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 3;
		contentPane.add(lblTelefone, gbc_lblTelefone);

		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridwidth = 4;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 3;
		contentPane.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);

		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Chama método para pegar um novo cliente..
				novoCliente();

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				salvarCliente();

			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 4;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirCliente();

			}

		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 6;
		gbc_btnNewButton_2.gridy = 4;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

		novoCliente();
	}

	private void novoCliente() {
		cliente = new Cliente();
	}

	private void salvarCliente() {

		try {

			cliente.setId(Integer.parseInt(txtID.getText()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCPF.getText());
		cliente.setTelefone(txtTelefone.getText());

		if (cliente.getId() == 0) {
			// Salvando o cliente...
			ClienteDao.gravar(cliente);
		} else {
			ClienteDao.alterar(cliente);
		}

		clearAllFields();
		JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");

		// Em seguida cria um novo cliente global..
		novoCliente();
	}

	private void excluirCliente() {

		int id = 0;

		try {

			id = Integer.parseInt(txtID.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (id != 0) {
			ClienteDao.excluir(id);

			clearAllFields();
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
		}

	}

	private void clearAllFields() {
		txtID.setText("");
		txtNome.setText("");
		txtCPF.setText("");
		txtTelefone.setText("");
	}

	private void clienteToFields(Cliente cliente2) {
		txtID.setText(String.valueOf(cliente2.getId()));
		txtNome.setText(cliente2.getNome());
		txtCPF.setText(cliente2.getCpf());
		txtTelefone.setText(cliente2.getTelefone());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
