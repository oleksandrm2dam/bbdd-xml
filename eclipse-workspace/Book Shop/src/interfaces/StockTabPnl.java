package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import entities.Book;
import entities.Client;
import entities.Stock;

public class StockTabPnl extends TabPnl {

	ArrayList<Stock> stocks = new ArrayList<Stock>();
	
	public StockTabPnl() {
		super();
		initStock();
	}

	
	public void initStock() {
		Stock stock = new Stock("asad1", 1, 15);
		Stock stock1 = new Stock("asad2", 2, 15);
		Stock stock2 = new Stock("asad3", 3, 15);
		Stock stock3 = new Stock("asad4", 4, 15);
		stocks = new ArrayList<Stock>();
		stocks.add(stock);
		stocks.add(stock1);
		stocks.add(stock2);
		stocks.add(stock3);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ISBN", "WAREHOUSECODE", "QUANTITY" }));
		table.setModel(new StockTblModel(stocks));
		scrollPane.setViewportView(table);
	}


	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, stocks);
		
	}


	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "ISBN": //searchStockISBN
			break;
		case "WAREHOUSECODE": //searchStockWarehouseCode
			break;
		case "QUANTITY": //searchStockQuantity
			break;
		}
		((StockTblModel)table.getModel()).setStocks(stocks);
		table.clearSelection();
		table.repaint();
	}


	@Override
	public void addAction() {
		StockDialog add = new StockDialog();
		add.setVisible(true);
		
	}


	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		Stock stock = new Stock();
		if(row>=0) {
			stock = ((StockTblModel)table.getModel()).getStock(row);
		}
		QuantityDialog edit = new QuantityDialog(stock);
		edit.setVisible(true);
	}

}
