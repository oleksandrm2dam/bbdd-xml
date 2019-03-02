package entities;

import java.util.ArrayList;
import java.util.Date;

public class Order {

	private int orderId;
	private ArrayList<String> booksISBN;
	private ArrayList<Integer> warehouseCodes;
	private String clientDNI;
	private Date date;
	private ArrayList<Integer> quantity;
	
	public Order(int orderId, ArrayList<String> booksISBN, ArrayList<Integer> quantity, Date date, String clientDNI) {
		super();
		this.orderId = orderId;
		this.booksISBN = booksISBN;
		this.quantity = quantity;
		this.date = date;
		this.clientDNI = clientDNI;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ArrayList<String> getIsbn() {
		return booksISBN;
	}

	public void setIsbn(ArrayList<String> booksISBN) {
		this.booksISBN = booksISBN;
	}

	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClientDNI() {
		return clientDNI;
	}

	public void setClientDNI(String clientDNI) {
		this.clientDNI = clientDNI;
	}
	
	public void setWarehouseCodes(ArrayList<Integer> warehouseCodes) {
		this.warehouseCodes = warehouseCodes;
	}
	
	public ArrayList<Integer> getWarehouseCodes(){
		return this.warehouseCodes;
	}
	
	
	
	
	
}
