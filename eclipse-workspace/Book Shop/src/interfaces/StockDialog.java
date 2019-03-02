package gui;

import javax.swing.JDialog;

import entities.Book;
import entities.Stock;
import entities.Warehouse;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;

public class StockDialog extends JDialog {
	private JTextField txtSearchBook;
	private JTextField txtsearchWarehouse;
	private JTextField txtISBN;
	private JTextField txtWarehouseCode;
	private JScrollPane spBooks;
	private JComboBox cbBooks;
	private JLabel lbBook;
	private JLabel lbWarehouse;
	private JComboBox cbWarehouses;
	private JScrollPane spWarehouses;
	private JLabel lbISBN;
	private JLabel lblWarehousecode;
	private JLabel lblQuantity;
	private JSpinner spinQuantity;
	private JButton btnAccept;
	private JButton btnCancel;
	private JTable tblBooks;
	private JTable tblWarehouses;
	private Stock stock;

	public StockDialog() {
		init();
		initAdd();
		// TODO Auto-generated constructor stub
	}
	
	public boolean empty() {
		return txtWarehouseCode.getText().equals("") || txtISBN.getText().equals("");
	}
	
	public void init(){
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 669, 570);
		
		
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
		btnCancel.setBounds(374, 310, 89, 23);
		getContentPane().add(btnCancel);
		

	}
	
	public void initAdd() {
		
		txtSearchBook = new JTextField();
		txtSearchBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				searchBooks();
			}
		});
		txtSearchBook.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				searchBooks();
			}
		});
		txtSearchBook.setBounds(110, 64, 200, 20);
		getContentPane().add(txtSearchBook);
		txtSearchBook.setColumns(10);
		
		spBooks = new JScrollPane();
		spBooks.setBounds(10, 95, 300, 119);
		getContentPane().add(spBooks);
		
		cbBooks = new JComboBox();
		cbBooks.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "TITLE"}));
		cbBooks.setBounds(10, 64, 90, 20);
		getContentPane().add(cbBooks);
		
		lbBook = new JLabel("Search Books");
		lbBook.setBounds(10, 39, 300, 14);
		getContentPane().add(lbBook);
		
		lbWarehouse = new JLabel("Search Warehouses");
		lbWarehouse.setBounds(10, 252, 300, 14);
		getContentPane().add(lbWarehouse);
		
		cbWarehouses = new JComboBox();
		cbWarehouses.setModel(new DefaultComboBoxModel(new String[] {"Code", "Name", "Population"}));
		cbWarehouses.setBounds(10, 277, 90, 20);
		getContentPane().add(cbWarehouses);
		
		txtsearchWarehouse = new JTextField();
		txtsearchWarehouse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchWarehouses();
			}
		});
		txtsearchWarehouse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				searchWarehouses();
			}
		});
		txtsearchWarehouse.setColumns(10);
		txtsearchWarehouse.setBounds(110, 277, 200, 20);
		getContentPane().add(txtsearchWarehouse);
		
		spWarehouses = new JScrollPane();
		spWarehouses.setBounds(10, 308, 300, 119);
		getContentPane().add(spWarehouses);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(374, 120, 180, 20);
		getContentPane().add(txtISBN);
		txtISBN.setColumns(10);
		
		txtWarehouseCode = new JTextField();
		txtWarehouseCode.setBounds(374, 176, 180, 20);
		getContentPane().add(txtWarehouseCode);
		txtWarehouseCode.setColumns(10);
		
		lbISBN = new JLabel("ISBN");
		lbISBN.setBounds(374, 95, 180, 14);
		getContentPane().add(lbISBN);
		
		lblWarehousecode = new JLabel("WarehouseCode");
		lblWarehousecode.setBounds(374, 151, 180, 14);
		getContentPane().add(lblWarehousecode);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(374, 207, 180, 14);
		getContentPane().add(lblQuantity);
		
		spinQuantity = new JSpinner();
		spinQuantity.setBounds(374, 232, 180, 20);
		getContentPane().add(spinQuantity);
		
		btnAccept = new JButton("Accept");
		btnAccept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				add();
			}
		});
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});
		btnAccept.setBounds(374, 276, 89, 23);
		getContentPane().add(btnAccept);

		tblBooks = new JTable();
		tblBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Book book = ((BookTblModel)tblBooks.getModel()).getBook(row);
					txtISBN.setText(book.getIsbn());
				}
			}
		});
		tblBooks.setModel(new BookTblModel());
		tblWarehouses = new JTable();
		tblWarehouses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Warehouse ware = ((WarehouseTblModel)tblWarehouses.getModel()).getWarehouse(row);
					txtWarehouseCode.setText(String.valueOf(ware.getCodWarehouse()));
				}
			}
		});
		tblWarehouses.setModel(new WarehouseTblModel());
		spBooks.setViewportView(tblBooks);
		spWarehouses.setViewportView(tblWarehouses);
	}
	
	private void add() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "FIll every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				Stock stock = new Stock();
				stock.setIsbn(txtISBN.getText());
				stock.setCodWarehouse(Integer.parseInt(txtWarehouseCode.getText()));
				stock.setQuantity((int)spinQuantity.getValue());
				//check if it exists
				//insert stock
				JOptionPane.showMessageDialog(new JDialog(), "Operation completed with success",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert an integer number in warehouse code film",
						"NumberFormatException", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void edit() {
		if(empty()) {
			JOptionPane.showMessageDialog(new JDialog(), "FIll every field",
					"Empty fields", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				stock.setQuantity((int)spinQuantity.getValue());
				//modify stock
				JOptionPane.showMessageDialog(new JDialog(), "Operation completed with success",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(new JDialog(), "Insert an integer number in warehouse code film",
						"NumberFormatException", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void searchWarehouses() {
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		switch((String)cbWarehouses.getSelectedItem()) {
		case "Code": //searchcode
			break;
		case "Name": //searchWarehouseName
			break;
		}
		((WarehouseTblModel)tblWarehouses.getModel()).setWarehouses(warehouses);
		tblWarehouses.repaint();
	}
	
	private void searchBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		switch((String)cbBooks.getSelectedItem()) {
		case "ISBN": //searchBooksISBN
			break;
		case "Title": //searchBooksTitle
			break;
		}
		((BookTblModel)tblBooks.getModel()).setBooks(books);
		tblBooks.repaint();
	}
}
