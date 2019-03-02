package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entities.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserDialog extends JDialog {
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtProfile;
	private User user;
	private JLabel lbName;
	private JLabel lbPassword;
	private JLabel lbProfile;
	private JButton btnAccept;
	private JButton btnCancel;
	
	public UserDialog() {
		init();
		initAdd();
	}
	
	public UserDialog(User user) {
		this.user = user;
		init();
		initMod();
	}
	
	private void init() {
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 437, 282);
		
		lbName = new JLabel("Name");
		lbName.setBounds(10, 28, 141, 14);
		getContentPane().add(lbName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 53, 141, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		lbPassword = new JLabel("Password");
		lbPassword.setBounds(212, 28, 141, 14);
		getContentPane().add(lbPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(212, 53, 141, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		lbProfile = new JLabel("Profile");
		lbProfile.setBounds(10, 113, 141, 14);
		getContentPane().add(lbProfile);
		
		txtProfile = new JTextField();
		txtProfile.setBounds(10, 138, 141, 20);
		getContentPane().add(txtProfile);
		txtProfile.setColumns(10);
		
		btnAccept = new JButton("Accept");
		btnAccept.setBounds(212, 137, 89, 23);
		getContentPane().add(btnAccept);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
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
		btnCancel.setBounds(311, 137, 89, 23);
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
		txtName.setText(user.getName());
		txtPassword.setText(user.getPassword());
		txtProfile.setText(user.getProfile());
		txtName.setEnabled(false);
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
		}else{
			User user = new User();
			user.setName(txtName.getText());
			user.setPassword(txtPassword.getText());
			user.setProfile(txtProfile.getText());
			//add user
		}
	}
	
	private void edit() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else{
			user.setPassword(txtPassword.getText());
			user.setProfile(txtProfile.getText());
			//modify user
		}
	}
	
	private boolean empty() {
		return txtName.getText().equals("") || txtPassword.getText().equals("") || txtProfile.getText().equals("");
	}
	

}
