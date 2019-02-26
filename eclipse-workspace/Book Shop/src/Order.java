package entities;

import java.util.Date;

public class Order {

	private String orderId;
	private String isbn;
	private int quantity;
	private Date date;
	private Client cliente;
	
	public Order(String orderId, String isbn, int quantity, Date date, Client cliente) {
		super();
		this.orderId = orderId;
		this.isbn = isbn;
		this.quantity = quantity;
		this.date = date;
		this.cliente = cliente;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
}
