package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entities.Book;
import entities.Stock;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;

public class main {

	private JFrame frame;
	private TabPnl bookPnl;
	private ClientTabPnl clientPnl;
	private OrderTabPnl orderPnl;
	private TabPnl stockPnl;
	private UserTabPnl userPnl;
	private WarehouseTabPnl warehousePnl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 709);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
	
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		bookPnl = new BookTabPnl();
		bookPnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(bookPnl);
		bookPnl.setLayout(new BorderLayout(0, 0));
		userPnl = new UserTabPnl();
		userPnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(userPnl);
		clientPnl = new ClientTabPnl();
		clientPnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(clientPnl);
		orderPnl = new OrderTabPnl();
		orderPnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(orderPnl);
		stockPnl = new StockTabPnl();
		stockPnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(stockPnl);
		stockPnl.setLayout(null);
		warehousePnl = new WarehouseTabPnl();
		warehousePnl.setBounds(0, 0, 434, 261);
		tabbedPane.add(warehousePnl);
		tabbedPane.setTitleAt(0, "Books");
		tabbedPane.setTitleAt(1, "Users");
		tabbedPane.setTitleAt(2, "Clients");
		tabbedPane.setTitleAt(3, "Orders");
		tabbedPane.setTitleAt(4, "Stock");
		tabbedPane.setTitleAt(5, "Warehouses");
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

}
