package gui;

import javax.swing.JDialog;
import javax.swing.JTextField;

import entities.Warehouse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WarehouseDialog extends JDialog {
	private JTextField txtWarehouseCode;
	private JTextField txtName;
	private JTextField txtPopulation;
	private JTextField txtTelephone;
	private JLabel lbWarehouseCode;
	private JButton btnCancel;
	private JButton btnAccept;
	private JLabel lbTelephone;
	private JLabel lbName;
	private JLabel lbEmail;
	private JLabel lbPopulation;
	private JTextField txtEmail;
	private JLabel lbNumberError;
	private Warehouse warehouse;
	
	public WarehouseDialog() {
		init();
		initAdd();
	}
	
	public WarehouseDialog(Warehouse warehouse) {
		this.warehouse = warehouse;
		init();
		initMod();
	}
	
	private void init() {
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 437, 282);
		
		txtWarehouseCode = new JTextField();
		txtWarehouseCode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					int code = Integer.parseInt(txtWarehouseCode.getText());
					//select warehouse with code
					lbNumberError.setVisible(false);
				}catch(NumberFormatException f) {
					lbNumberError.setVisible(true);
				}
			}
		});
		txtWarehouseCode.setBounds(10, 47, 167, 20);
		getContentPane().add(txtWarehouseCode);
		txtWarehouseCode.setColumns(10);
		
		lbWarehouseCode = new JLabel("Warehouse Code");
		lbWarehouseCode.setBounds(10, 22, 89, 14);
		getContentPane().add(lbWarehouseCode);
		
		txtName = new JTextField();
		txtName.setBounds(233, 47, 178, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPopulation = new JTextField();
		txtPopulation.setBounds(10, 108, 167, 20);
		getContentPane().add(txtPopulation);
		txtPopulation.setColumns(10);
		
		txtTelephone = new JTextField();
		txtTelephone.setBounds(233, 108, 178, 20);
		getContentPane().add(txtTelephone);
		txtTelephone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 172, 167, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		lbPopulation = new JLabel("Population");
		lbPopulation.setBounds(10, 83, 167, 14);
		getContentPane().add(lbPopulation);
		
		lbEmail = new JLabel("Email");
		lbEmail.setBounds(10, 147, 167, 14);
		getContentPane().add(lbEmail);
		
		lbName = new JLabel("Name");
		lbName.setBounds(244, 22, 167, 14);
		getContentPane().add(lbName);
		
		lbTelephone = new JLabel("Telephone");
		lbTelephone.setBounds(254, 78, 167, 14);
		getContentPane().add(lbTelephone);
		
		btnAccept = new JButton("Accept");

	
		btnAccept.setBounds(233, 171, 89, 23);
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
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(332, 171, 89, 23);
		getContentPane().add(btnCancel);
		
		lbNumberError = new JLabel("*Must be Integer");
		lbNumberError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNumberError.setBounds(113, 22, 108, 14);
		getContentPane().add(lbNumberError);
		lbNumberError.setVisible(false);
	}
	
	private void initMod() {
		txtWarehouseCode.setText(String.valueOf(warehouse.getCodWarehouse()));
		txtWarehouseCode.setEnabled(false);
		txtName.setText(warehouse.getName());
		txtPopulation.setText(warehouse.getPopulation());
		txtTelephone.setText(warehouse.getTelephone());
		txtEmail.setText(warehouse.getEmail());
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	
	private void initAdd() {
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	
	private void add() {
		try {
			if(empty()) {
				JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
						"Empty fields", JOptionPane.ERROR_MESSAGE);
			}else {
				Warehouse ware = new Warehouse();
				int code = Integer.parseInt(txtWarehouseCode.getText());
				String name = txtName.getText();
				String population = txtPopulation.getText();
				String telephone  = txtTelephone.getText();
				String email = txtEmail.getText();
				ware.setCodWarehouse(code);
				ware.setName(name);
				ware.setPopulation(population);
				ware.setTelephone(telephone);
				ware.setEmail(email);
				//ADD WAREHOUSE
				JOptionPane.showMessageDialog(new JDialog(), "Operation completed with succes",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(NumberFormatException f) {
			JOptionPane.showMessageDialog(new JDialog(), "Insert an integer number in warehouse code field",
					"NumberFormatException", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void edit() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else {
			String name = txtName.getText();
			String population = txtPopulation.getText();
			String telephone  = txtTelephone.getText();
			String email = txtEmail.getText();
			warehouse.setName(name);
			warehouse.setPopulation(population);
			warehouse.setTelephone(telephone);
			warehouse.setEmail(email);
			//MODIFY WAREHOUSE
			JOptionPane.showMessageDialog(new JDialog(), "Operation completed with succes",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean empty() {
		return txtEmail.getText().equals("") || txtName.getText().equals("") || txtPopulation.getText().equals("") 
				|| txtTelephone.getText().equals("") || txtWarehouseCode.getText().equals("");
	}
}
