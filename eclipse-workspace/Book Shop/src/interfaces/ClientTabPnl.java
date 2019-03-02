package gui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import entities.Book;
import entities.Client;

public class ClientTabPnl extends TabPnl {
	
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	public ClientTabPnl() {
		super();
		initClient();
	}

	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, clients);
	}

	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "ID": //searchClientID
			break;
		case "DNI": //searchClientDNI
			break;
		}
		((ClientTblModel)table.getModel()).setClients(clients);
		table.clearSelection();
		table.repaint();
	}

	@Override
	public void addAction() {
		ClientDialog add = new ClientDialog();
		add.setVisible(true);
		
	}

	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		Client client= new Client();
		if(row>=0) {
			client = ((ClientTblModel)table.getModel()).getClient(row);
		}
		ClientDialog editPnl = new ClientDialog(client);
		editPnl.setVisible(true);
	}
	
	public void initClient() {
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ID", "DNI"}));
		table.setModel(new ClientTblModel());
		scrollPane.setViewportView(table);
	}

	
}
