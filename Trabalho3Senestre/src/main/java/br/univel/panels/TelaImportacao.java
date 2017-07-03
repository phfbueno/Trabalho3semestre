package br.univel.panels;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ProdutoDao;
import br.univel.domain.Produto;
import br.univel.domain.ProdutoParser;
import br.univel.domain.ReaderURL;

public class TelaImportacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641331956587094015L;
	
	private JPanel contentPane;
	private JTextPane paneLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaImportacao frame = new TelaImportacao();
					frame.setVisible(true);
					frame.setSize(600, 400);
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
	public TelaImportacao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnImportarProdutos = new JButton("Importar Produtos");
		btnImportarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				importarProdutos();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 7;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		paneLog = new JTextPane();
		paneLog.setFont(new Font("Tahoma", Font.BOLD, 12));
		paneLog.setEnabled(false);
		paneLog.setEditable(false);
		scrollPane.setViewportView(paneLog);
		GridBagConstraints gbc_btnImportarProdutos = new GridBagConstraints();
		gbc_btnImportarProdutos.insets = new Insets(0, 0, 0, 5);
		gbc_btnImportarProdutos.gridx = 7;
		gbc_btnImportarProdutos.gridy = 6;
		contentPane.add(btnImportarProdutos, gbc_btnImportarProdutos);
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
	}
	
	
	private void importarProdutos() {
	
		ReaderURL reader = new ReaderURL();
		List<String> lista = reader.lerUrl();

		ProdutoParser parser = new ProdutoParser();
		List<Produto> listaProdutos = parser.getProduto(lista);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {				
				listaProdutos.forEach(e -> {
					
					paneLog.setText(paneLog.getText() + "\n" + "Inserindo registro " + e.getId() + " \t " + e.getDescricao() + "");
					paneLog.setCaretPosition(paneLog.getDocument().getLength());
					ProdutoDao.gravar(e);

				});
			}
		};
		
		
		new Thread(runnable).start();
		
	}
}
