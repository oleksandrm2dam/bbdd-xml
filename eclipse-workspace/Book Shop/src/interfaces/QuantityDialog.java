package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import entities.Stock;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuantityDialog extends JDialog{
	
	private Stock stock;
	
	public QuantityDialog(Stock stock){
		setModal(true);
		this.stock = stock;
		getContentPane().setLayout(null);
		setBounds(0, 0, 430, 333);
		
		JLabel lbISBN = new JLabel("ISBN: " + stock.getIsbn());
		lbISBN.setBounds(85, 32, 259, 37);
		getContentPane().add(lbISBN);
		
		JLabel lbCode = new JLabel("CODE: " + stock.getCodWarehouse());
		lbCode.setBounds(85, 80, 259, 33);
		getContentPane().add(lbCode);
		
		JSpinner spinQuanity = new JSpinner();
		spinQuanity.setBounds(170, 124, 64, 37);
		getContentPane().add(spinQuanity);
		
		spinQuanity.setValue(stock.getQuantity());
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int value = (int) spinQuanity.getValue();
					if(value >=0 ) {
						if(value > stock.getQuantity()) {
							JOptionPane.showMessageDialog(new JDialog(), "There is no enough stock existences in stock is: " + stock.getQuantity(),
									"NotEnoughStock", JOptionPane.ERROR_MESSAGE);
						}else {
							stock.setQuantity(value);
							dispose();
						}
					}else {
						System.out.println("Must be positive number");
					}
				}
			}
		});
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (int) spinQuanity.getValue();
				if(value >=0 ) {
					stock.setQuantity(value);
					dispose();
				}else {
					System.out.println("Must be positive number");
				}
			}
		});
		btnAccept.setBounds(85, 188, 89, 23);
		getContentPane().add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
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
		btnCancel.setBounds(231, 188, 89, 23);
		getContentPane().add(btnCancel);
		
	}
	
	

}
