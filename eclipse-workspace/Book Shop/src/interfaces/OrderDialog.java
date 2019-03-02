package gui;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import entities.Book;
import entities.Order;
import entities.Stock;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

public class OrderDialog extends JDialog {
	private JTextField txtSearchStock;
	private JTextField txtID;
	private JComboBox comboBox;
	private JScrollPane spAddBooks;
	private JScrollPane spBooksAdded;
	private JLabel lblAddedBooks;
	private JLabel lblBooksAdd;
	private JSpinner spinQuantity;
	private JLabel lbOrderId;
	private JButton btnAccept;
	private JButton btnCancel;
	private Order order;
	private Order orderMod;
	private JTable tblAddBooks;
	private JTable tblBooksAdded;
	private JLabel lbClientDNI;
	private JTextField txtClientDNI;
	private ArrayList<Stock> stocks;
	private ArrayList<Stock> filteredStocks;
	
	public OrderDialog() {
		init();
	}
	
	public OrderDialog(Order order) {
		this.orderMod = order;
		init();
		initMod();
	}
	
	private void init() {
		getContentPane().setLayout(null);
		setBounds(0, 0, 801, 523);
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "TITLE", "CODE"}));
		comboBox.setBounds(10, 159, 95, 20);
		getContentPane().add(comboBox);
		
		txtSearchStock = new JTextField();
		txtSearchStock.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				searchStock();
			}
		});
		txtSearchStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchStock();
				}
			}
		});
		txtSearchStock.setBounds(115, 159, 195, 20);
		getContentPane().add(txtSearchStock);
		txtSearchStock.setColumns(10);
		
		spAddBooks = new JScrollPane();
		spAddBooks.setBounds(10, 190, 350, 143);
		getContentPane().add(spAddBooks);
		
		spBooksAdded = new JScrollPane();
		spBooksAdded.setBounds(397, 190, 344, 143);
		getContentPane().add(spBooksAdded);
		
		lblAddedBooks = new JLabel("Added Books");
		lblAddedBooks.setBounds(397, 162, 344, 14);
		getContentPane().add(lblAddedBooks);
		
		lblBooksAdd = new JLabel("Books to add");
		lblBooksAdd.setBounds(10, 134, 350, 14);
		getContentPane().add(lblBooksAdd);
		
		spinQuantity = new JSpinner();
		spinQuantity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				filterStocks();
				((StockTblModel)tblAddBooks.getModel()).setStocks(filteredStocks);
				tblAddBooks.repaint();
			}
		});
		spinQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filterStocks();
				((StockTblModel)tblAddBooks.getModel()).setStocks(filteredStocks);
				tblAddBooks.repaint();
			}
		});
		spinQuantity.setBounds(320, 159, 40, 20);
		getContentPane().add(spinQuantity);
		
		lbOrderId = new JLabel("Order ID:");
		lbOrderId.setBounds(10, 11, 95, 14);
		getContentPane().add(lbOrderId);
		
		txtID = new JTextField();
		txtID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//searchOrderid
			}
		});
		txtID.setBounds(10, 30, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		btnAccept = new JButton("Accept");
		btnAccept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					add();
				}
			}
		});
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});
		btnAccept.setBounds(10, 359, 89, 23);
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
		btnCancel.setBounds(115, 359, 89, 23);
		getContentPane().add(btnCancel);
		
		tblBooksAdded = new JTable();
		tblBooksAdded.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					int [] rows = tblBooksAdded.getSelectedRows();
					StockTblModel model = (StockTblModel) tblBooksAdded.getModel();
					ArrayList<Stock> stocks = model.getStocks();
					for( int index : rows) {
						stocks.remove(index);
					}
					model.setStocks(stocks);
					tblBooksAdded.setModel(model);
					tblBooksAdded.repaint();
				}
			}
		});
		tblBooksAdded.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Stock stock = ((StockTblModel)tblBooksAdded.getModel()).getStock(row);
					QuantityDialog quantity = new QuantityDialog(stock);
					quantity.setVisible(true);
					tblBooksAdded.repaint();
				}
			}
		});
		tblAddBooks = new JTable();
		tblAddBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Stock stock = ((StockTblModel)tblAddBooks.getModel()).getStock(row);
					QuantityDialog quantity = new QuantityDialog(stock);
					quantity.setVisible(true);
					((StockTblModel)tblBooksAdded.getModel()).addStock(stock);
					tblBooksAdded.repaint();
				}
			}
		});
		tblBooksAdded.setModel(new StockTblModel());
		tblAddBooks.setModel(new StockTblModel());
		spAddBooks.setViewportView(tblAddBooks);
		spBooksAdded.setViewportView(tblBooksAdded);
		
		lbClientDNI = new JLabel("Client DNI:");
		lbClientDNI.setBounds(10, 61, 158, 14);
		getContentPane().add(lbClientDNI);
		
		txtClientDNI = new JTextField();
		txtClientDNI.setBounds(10, 86, 158, 20);
		getContentPane().add(txtClientDNI);
		txtClientDNI.setColumns(10);
	}
	
	private void searchStock() {
		stocks = new ArrayList<Stock>();
		filteredStocks = new ArrayList<Stock>();
		switch((String)comboBox.getSelectedItem()) {
		case "ISBN": //searchStockISBN
			break;
		case "TITLE": //SearchStockTitle
			break;
		case "CODE": //SearchStockWarehouseCode
			break;
		}
		filterStocks();
		((StockTblModel)tblAddBooks.getModel()).setStocks(filteredStocks);
		tblAddBooks.repaint();
	}
	
	private void initMod() {
		txtClientDNI.setText(orderMod.getClientDNI());
		txtID.setText(String.valueOf(orderMod.getOrderId()));
		ArrayList<Stock> stocksMod = new ArrayList<Stock>();
		ArrayList<String> isbn = orderMod.getIsbn();
		ArrayList<Integer> codes = orderMod.getWarehouseCodes();
		ArrayList<Integer> quantities = orderMod.getQuantity();
		System.out.println(quantities.get(0));
		System.out.println(codes.get(1));
		for(int i = 0; i<isbn.size(); ++i) {
			System.out.println(i);
			stocksMod.add(new Stock(isbn.get(i), codes.get(i), quantities.get(i)));
		}
		((StockTblModel)tblBooksAdded.getModel()).setStocks(stocksMod);
		tblBooksAdded.repaint();
	}
	
	private void add() {
		Order order = new Order();
		try {
			order.setOrderId(Integer.parseInt(txtID.getText()));
			order.setClientDNI(txtClientDNI.getText());
			order.setDate(new Date());
			ArrayList<String> books = new ArrayList<String>();
			ArrayList<Integer> warehouseCodes = new ArrayList<Integer>();
			ArrayList<Integer> quantities = new ArrayList<Integer>();
			ArrayList<Stock> stocks = ((StockTblModel)tblBooksAdded.getModel()).getStocks();
			for(Stock stock: stocks){
				books.add(stock.getIsbn());
				warehouseCodes.add(stock.getCodWarehouse());
				quantities.add(stock.getQuantity());
			}
			order.setIsbn(books);
			order.setWarehouseCodes(warehouseCodes);
			order.setQuantity(quantities);
			//Insert Order
			dispose();
		}catch(NumberFormatException f) {
			System.out.println("number format exception");
		}
	}
	
	private void filterStocks() {
		int quantity = (int)spinQuantity.getValue();
		for(Stock stock : stocks) {
			if(stock.getQuantity() > quantity) {
				filteredStocks.add(stock);
			}
		}
	}
	
	private void edit() {
		
	}
}
