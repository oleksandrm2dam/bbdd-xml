package entities;

public class User {

	private String name;
	private String password;
	private String profile;

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, String profile) {
		super();
		this.name = name;
		this.password = password;
		this.profile = profile;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
