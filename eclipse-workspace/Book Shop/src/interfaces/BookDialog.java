package gui;

import javax.swing.JDialog;
import javax.swing.JTextField;

import entities.Book;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookDialog extends JDialog{
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtPublisher;
	private JTextField txtEdition;
	private JTextField txtDate;
	private JTextField txtPrice;
	private JLabel lbExistsError;
	private Book book;
	private JTextField txtIsbn;
	private JButton btnAdd;
	private JButton btnCancel;
	private boolean valid = true;
	private JLabel lbErrorFloat;
	private JLabel lbDateFormat;
	
	public BookDialog() {
		super();
		init();
		initAdd();
	}
	
	public BookDialog(Book book) {
		super();
		init();
		this.book = book;
		initMod();
	}
	private void init() {
		getContentPane().setLayout(null);
		setModal(true);
		setBounds(0, 0, 620, 337);

		txtTitle = new JTextField();
		txtTitle.setBounds(234, 29, 161, 20);
		getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(10, 70, 161, 20);
		getContentPane().add(txtAuthor);
		txtAuthor.setColumns(10);
		
		txtPublisher = new JTextField();
		txtPublisher.setBounds(234, 70, 161, 20);
		getContentPane().add(txtPublisher);
		txtPublisher.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setBounds(10, 114, 161, 20);
		getContentPane().add(txtEdition);
		txtEdition.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setBounds(234, 114, 161, 20);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);
		txtDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(txtDate.getText());
					lbDateFormat.setVisible(false);
				}catch(ParseException f) {
					lbDateFormat.setVisible(true);
				}
			}
		});
		
		txtPrice = new JTextField();
		txtPrice.setBounds(10, 157, 161, 20);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					float price = Float.parseFloat(txtPrice.getText());
					lbErrorFloat.setVisible(false);
				}catch(NumberFormatException f) {
					lbErrorFloat.setVisible(true);
				}
			}
		});
		
		JLabel lbTitle = new JLabel("Title");
		lbTitle.setBounds(234, 11, 161, 14);
		getContentPane().add(lbTitle);
		
		JLabel lbAuthor = new JLabel("Author");
		lbAuthor.setBounds(10, 57, 161, 14);
		getContentPane().add(lbAuthor);
		
		JLabel lbPublisher = new JLabel("Publisher");
		lbPublisher.setBounds(234, 57, 161, 14);
		getContentPane().add(lbPublisher);
		
		JLabel lbEdition = new JLabel("Edition");
		lbEdition.setBounds(12, 98, 159, 14);
		getContentPane().add(lbEdition);
		
		JLabel lbDate = new JLabel("Publishing date");
		lbDate.setBounds(234, 101, 131, 14);
		getContentPane().add(lbDate);
		
		JLabel lbPrice = new JLabel("Price");
		lbPrice.setBounds(10, 145, 63, 14);
		getContentPane().add(lbPrice);
		
		btnAdd = new JButton("Add");


		btnAdd.setBounds(234, 156, 161, 23);
		getContentPane().add(btnAdd);
		
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
		btnCancel.setBounds(233, 190, 162, 23);
		getContentPane().add(btnCancel);
		
		//Error messages
		lbExistsError = new JLabel("* Already exists");
		lbExistsError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbExistsError.setBounds(93, 11, 131, 14);
		getContentPane().add(lbExistsError);
		lbErrorFloat = new JLabel("*Insert a float number");
		lbErrorFloat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbErrorFloat.setBounds(74, 145, 139, 14);
		getContentPane().add(lbErrorFloat);
		lbDateFormat = new JLabel("*Format must be (dd/MM/yyyy)");
		lbDateFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbDateFormat.setBounds(375, 101, 189, 14);
		getContentPane().add(lbDateFormat);
		lbExistsError.setVisible(false);
		lbErrorFloat.setVisible(false);
		lbDateFormat.setVisible(false);
	}
	
	private boolean empty() {
		return txtIsbn.getText().equals("") || txtTitle.getText().equals("") || txtAuthor.getText().equals("") ||
				txtPublisher.getText().equals("") || txtEdition.getText().equals("") || txtDate.getText().equals("");
	}
	
	private void initAdd() {
		
		
		txtIsbn = new JTextField();
		txtIsbn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				//selectBook(String isbn)
			}
		});
		txtIsbn.setBounds(10, 29, 161, 20);
		getContentPane().add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lbIsbn = new JLabel("ISBN");
		lbIsbn.setBounds(10, 11, 73, 14);
		getContentPane().add(lbIsbn);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					add();
				}
			}
		});
	
	}
	
	private void initMod() {
		txtIsbn = new JTextField();
		txtIsbn.setBounds(10, 29, 161, 20);
		getContentPane().add(txtIsbn);
		txtIsbn.setColumns(10);
		txtIsbn.setEnabled(false);
		txtIsbn.setVisible(false);
		
		JLabel lbIsbn = new JLabel("ISBN: " + book.getIsbn());
		lbIsbn.setBounds(10, 11, 161, 14);
		getContentPane().add(lbIsbn);
		
		txtAuthor.setText(book.getAuthor());
		txtTitle.setText(book.getTitle());
		txtPublisher.setText(book.getPublisher());
		txtEdition.setText(book.getEdition());
		txtDate.setText(String.valueOf(book.getPublishingDate()));
		txtPrice.setText(String.valueOf(book.getPrice()));
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mod();
			}
		});
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					mod();
				}
			}
		});
	}
	
	private void add() {
		book = new Book();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else if(valid){
			try {
			book.setIsbn(txtIsbn.getText());
			book.setTitle(txtTitle.getText());
			book.setAuthor(txtAuthor.getText());
			book.setPublisher(txtPublisher.getText());
			book.setEdition(txtEdition.getText());
			book.setPublishingDate(sdf.parse(txtDate.getText()));
			book.setPrice(Float.parseFloat(txtPrice.getText()));
			System.out.println(book.toString());
			//insertarLibro
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert a float number in price field",
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
			}catch(ParseException g) {
				JOptionPane.showMessageDialog(new JDialog(), "Invalid date format. The valid format is (dd/MM/yyyy)",
						"Invalid Date Format", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void mod() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "Fill every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else if(valid){
			try {
			book.setTitle(txtTitle.getText());
			book.setAuthor(txtAuthor.getText());
			book.setPublisher(txtPublisher.getText());
			book.setEdition(txtEdition.getText());
			book.setPublishingDate(sdf.parse(txtDate.getText()));
			book.setPrice(Float.parseFloat(txtPrice.getText()));
			System.out.println(book.toString());
			//modificarLibro
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert a float number in price field",
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
			}catch(ParseException g) {
				JOptionPane.showMessageDialog(new JDialog(), "Invalid date format. The valid format is (dd/MM/yyyy)",
						"Invalid Date Format", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
