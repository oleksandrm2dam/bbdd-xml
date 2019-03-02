package entities;

public class Stock {

	private String isbn;
	private int codWarehouse;
	private int quantity;
	
	public Stock(String isbn, int codWarehouse, int quantity){
		this.isbn=isbn;
		this.codWarehouse=codWarehouse;
		this.quantity=quantity;
	}

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getCodWarehouse() {
		return codWarehouse;
	}

	public void setCodWarehouse(int codWarehouse) {
		this.codWarehouse = codWarehouse;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
