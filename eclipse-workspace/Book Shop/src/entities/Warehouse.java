package entities;

public class Warehouse {

	private int codWarehouse;
	private String name;
	private String population;
	private String telephone;
	private String email;
	
	
	public Warehouse(int codWarehouse, String name, String population,
			String telephone, String email) {
		super();
		this.codWarehouse = codWarehouse;
		this.name = name;
		this.population = population;
		this.telephone = telephone;
		this.email = email;
	}
	
	public Warehouse() {
		// TODO Auto-generated constructor stub
	}

	public int getCodWarehouse() {
		return codWarehouse;
	}
	public void setCodWarehouse(int codWarehouse) {
		this.codWarehouse = codWarehouse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
	
}
