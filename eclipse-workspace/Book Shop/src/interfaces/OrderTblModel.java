package gui;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import entities.Order;

public class OrderTblModel extends AbstractTableModel {


	
	private String[] columnNames = { "ORDERID", "CLIENTDNI", "DATE"};
	private ArrayList<Order> orders;

	public OrderTblModel() {

	}

	public OrderTblModel(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public int getRowCount() {
		if (orders == null) {
			return 0;
		} else {
			return orders.size();
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
			return (int) orders.get(row).getOrderId();
		case 1:
			return (String) orders.get(row).getClientDNI();
		case 2:
			return (Date) orders.get(row).getDate();
		default:
			return null;
		}
		
	}
	
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public Order getOrder(int row) {
		int id = (int) getValueAt(row,0);
		for(Order order: orders) {
			if(order.getOrderId() == id) {
				return order;
			}
		}
		return null;
	}

}
