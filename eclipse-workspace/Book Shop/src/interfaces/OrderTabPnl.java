package gui;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import entities.Book;
import entities.Order;

public class OrderTabPnl extends TabPnl {

	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public OrderTabPnl() {
		super();
		Order order = new Order();
		order.setOrderId(1);
		order.setClientDNI("dni1");
		order.setDate(new Date());
		ArrayList<String> isbns = new ArrayList<String>();
		isbns.add("isbn1");
		isbns.add("isbn2");
		isbns.add("isbn1");
		isbns.add("isbn4");
		ArrayList<Integer> codes = new ArrayList<Integer>();
		codes.add(1);
		codes.add(2);
		codes.add(3);
		codes.add(4);
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		quantities.add(12);
		quantities.add(20);
		quantities.add(30);
		quantities.add(10);
		order.setIsbn(isbns);
		order.setWarehouseCodes(codes);
		order.setQuantity(quantities);
		orders.add(order);
		initOrder();
	}
	
	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, orders);
	}

	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "ORDERID": //searchOrderID
			break;
		case "CLIENTDNI": //searchOrderClientDNI
			break;
		case "DATE": //searchOrderDate
			break;
		}
		((OrderTblModel)table.getModel()).setOrders(orders);
		table.clearSelection();
		table.repaint();
	}

	@Override
	public void addAction() {
		OrderDialog add = new OrderDialog();
		add.setVisible(true);
	}

	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		Order order = new Order();
		if(row>=0) {
			order = ((OrderTblModel)table.getModel()).getOrder(row);
		}
		System.out.println(order.getIsbn() + "   " + order.getClientDNI());
		OrderDialog addPnl = new OrderDialog(order);
		addPnl.setVisible(true);
	}
	
	
	public void initOrder(){
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ORDERID", "CLIENTDNI", "DATE"}));
		table.setModel(new OrderTblModel(orders));
		scrollPane.setViewportView(table);
	}



}
