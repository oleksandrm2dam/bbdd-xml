package gui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import entities.Stock;
import entities.User;

public class UserTabPnl extends TabPnl {

	private ArrayList<User> users = new ArrayList<User>();
	
	public UserTabPnl() {
		super();
		initUser();
	}
	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, users);
	}

	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "NAME": //searchUserName
			break;
		case "PROFILE": //searchUserProfile
			break;
		}
		((UserTblModel)table.getModel()).setUsers(users);
		table.clearSelection();
		table.repaint();
	}

	@Override
	public void addAction() {
		UserDialog add = new UserDialog();
		add.setVisible(true);
		
	}

	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		User user = new User();
		if(row>=0) {
			user = ((UserTblModel)table.getModel()).getUser(row);
		}
		UserDialog edit = new UserDialog(user);
		edit.setVisible(true);
	}

	public void initUser() {
		User user1 = new User();
		user1.setName("user1");
		user1.setPassword("password1");
		user1.setProfile("profile");
		users.add(user1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "NAME", "PASSWORD", "PROFILE" }));
		table.setModel(new UserTblModel());
		((UserTblModel)table.getModel()).setUsers(users);
		scrollPane.setViewportView(table);
	}

}
