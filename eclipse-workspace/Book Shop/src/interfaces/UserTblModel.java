package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.User;

public class UserTblModel extends AbstractTableModel {

	private String[] columnNames = { "NAME", "PASSWORD", "PROFILE"};
	private ArrayList<User> users;
	
	public UserTblModel() {
		 users = new ArrayList<User>();
	}

	public UserTblModel(ArrayList<User> users) {
		this.users = users;
	}

	public int getRowCount() {
		if (users == null) {
			return 0;
		} else {
			return users.size();
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
			return users.get(row).getName();
		case 1:
			return users.get(row).getPassword();
		case 2:
			return users.get(row).getProfile();
		default:
			return null;
		}
		
	}

	public User getUser(int row) {
		User user = new User();
		String name = (String) getValueAt(row,0);
		String password = (String) getValueAt(row,1);
		String profile = (String) getValueAt(row,2);
		user.setName(name);
		user.setPassword(password);
		user.setProfile(profile);
		return user;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
