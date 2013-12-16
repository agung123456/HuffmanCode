import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class HuffmanCode extends javax.swing.JFrame {

	File file;
	Huffman huffman = new Huffman();

	public HuffmanCode() {
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new JLabel();
		txtPath = new JTextField();
		btnBrowse = new JButton();
		btnCompress = new JButton();
		btnDecompress = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("File Name :");

		btnBrowse.setText("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBrowseActionPerformed(evt);
			}
		});

		btnCompress.setText("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCompressActionPerformed(evt);
			}
		});

		btnDecompress.setText("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDecompressActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnCompress,
																		GroupLayout.PREFERRED_SIZE,
																		110,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		btnDecompress,
																		GroupLayout.PREFERRED_SIZE,
																		110,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		65,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		txtPath)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnBrowse)))
								.addContainerGap()));
		
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap(14, Short.MAX_VALUE)
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
										.addComponent(btnBrowse)
										.addComponent(txtPath,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING, false)
										.addComponent(btnDecompress,
												GroupLayout.DEFAULT_SIZE, 32,
												Short.MAX_VALUE)
										.addComponent(btnCompress,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGap(17, 17, 17)));

		pack();
	}

	private void btnBrowseActionPerformed(ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();

		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.file = fileChooser.getSelectedFile();
			txtPath.setText(file.getAbsolutePath());
		}
	}

	private void btnCompressActionPerformed(ActionEvent evt) {
		huffman.compress(txtPath.getText());
		JOptionPane.showMessageDialog(this, "Action Completed");
	}

	private void btnDecompressActionPerformed(java.awt.event.ActionEvent evt) {
		huffman.decompress(txtPath.getText());
		JOptionPane.showMessageDialog(this, "Action Completed");
	}

	public static void main(String args[]) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HuffmanCode().setVisible(true);
			}
		});
	}

	private javax.swing.JButton btnCompress;
	private javax.swing.JButton btnDecompress;
	private javax.swing.JButton btnBrowse;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField txtPath;
}
