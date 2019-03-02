package gui;

import javax.swing.JDialog;
import javax.swing.JTextField;

import entities.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientDialog extends JDialog {
	private JTextField txtId;
	private JTextField txtDNI;
	private JTextField txtName;
	private JTextField txtSurnames;
	private JLabel lbId;
	private JLabel lbDNI;
	private JLabel lbName;
	private JLabel lbSurnames;
	private JButton btnAccept;
	private JButton btnCancel;
	private Client client;
	
	public ClientDialog() {
		init();
		initAdd();
	}
	
	public ClientDialog(Client client) {
		this.client = client;
		init();
		initMod();
	}
	
	private void init() {
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 437, 282);
		
		txtId = new JTextField();
		txtId.setBounds(10, 42, 160, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(219, 42, 160, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(10, 131, 160, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtSurnames = new JTextField();
		txtSurnames.setBounds(219, 131, 160, 20);
		getContentPane().add(txtSurnames);
		txtSurnames.setColumns(10);
		
		lbId = new JLabel("ID");
		lbId.setBounds(10, 17, 160, 14);
		getContentPane().add(lbId);
		
		lbDNI = new JLabel("DNI");
		lbDNI.setBounds(219, 17, 160, 14);
		getContentPane().add(lbDNI);
		
		lbName = new JLabel("Name");
		lbName.setBounds(10, 106, 160, 14);
		getContentPane().add(lbName);
		
		lbSurnames = new JLabel("Surnames");
		lbSurnames.setBounds(219, 106, 160, 14);
		getContentPane().add(lbSurnames);
		
		btnAccept = new JButton("Accept");


		btnAccept.setBounds(10, 185, 89, 23);
		getContentPane().add(btnAccept);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(109, 185, 89, 23);
		getContentPane().add(btnCancel);
	}
	
	private void initAdd() {
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		btnAccept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					add();
				}
			}
		});
	}
	
	private void initMod() {
		txtId.setText(String.valueOf(client.getId()));
		txtId.setEnabled(false);
		txtDNI.setText(client.getDni());
		txtName.setText(client.getName());
		txtSurnames.setText(client.getSurnames());
		
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		btnAccept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					edit();
				}
			}
		});
	}
	
	private void add() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				client = new Client();
				client.setId(Integer.parseInt(txtId.getText()));
				client.setDni(txtDNI.getText());
				client.setName(txtName.getText());
				client.setSurnames(txtSurnames.getText());
				//add client
				JOptionPane.showMessageDialog(new JDialog(), "Operation completed with success",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert an integer in id field",
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void edit() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				client.setDni(txtDNI.getText());
				client.setName(txtName.getText());
				client.setSurnames(txtSurnames.getText());
				//mod client
				JOptionPane.showMessageDialog(new JDialog(), "Operation completed with success",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert an integer in id field",
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean empty() {
		return txtDNI.getText().equals("") || txtId.getText().equals("") || txtName.getText().equals("") || txtSurnames.getText().equals("");
	}

}
