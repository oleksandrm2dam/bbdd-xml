package entities;

public class Stock {

	private String isbn;
	private String codWarehouse;
	private int quantity;
	
	public Stock(String isbn, String codWarehouse, int quantity){
		this.isbn=isbn;
		this.codWarehouse=codWarehouse;
		this.quantity=quantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCodWarehouse() {
		return codWarehouse;
	}

	public void setCodWarehouse(String codWarehouse) {
		this.codWarehouse = codWarehouse;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
