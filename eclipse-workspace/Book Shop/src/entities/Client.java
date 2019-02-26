package entities;

public class Client {

	private int id;
	private String dni;
	private String name;
	private String surnames;
	
	public Client(String dni, String name, String surnames) {
	this.dni = dni;
	this.name = name;
	this.surnames = surnames;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	
	
	
}
