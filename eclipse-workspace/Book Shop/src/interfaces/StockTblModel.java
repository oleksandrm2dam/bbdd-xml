package gui;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import entities.Book;
import entities.Stock;
import entities.Warehouse;

public class StockTblModel extends AbstractTableModel {

	
	private String[] columnNames = { "ISBN", "Warehouse", "Quantity"};
	private ArrayList<Stock> stocks;

	public StockTblModel() {

	}

	public StockTblModel(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	public int getRowCount() {
		if (stocks == null) {
			return 0;
		} else {
			return stocks.size();
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return (String) stocks.get(row).getIsbn();
		case 1:
			return (int) stocks.get(row).getCodWarehouse();
		case 2:
			return (int) stocks.get(row).getQuantity();
		default:
			return null;
		}
		
	}

	public Stock getStock(int row) {
		Stock stock = new Stock();
		String isbn = (String) getValueAt(row,0);
		int codwarehouse = (int) getValueAt(row,1);
		int quantity = (int) getValueAt(row,2);
		stock.setIsbn(isbn);
		stock.setCodWarehouse(codwarehouse);
		stock.setQuantity(quantity);
		return stock;
	}
	
	public void addStock(Stock stock) {
		this.stocks.add(stock);
	}
	
	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public ArrayList<Stock> getStocks() {
		return this.stocks;
	}


}
