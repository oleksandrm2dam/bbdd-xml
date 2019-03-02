package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.Client;
import entities.Warehouse;

public class ClientTblModel extends AbstractTableModel {

	
	private String[] columnNames = { "ID", "DNI", "NAME", "SURNAMES"};
	private ArrayList<Client> clients;

	public ClientTblModel() {

	}

	public ClientTblModel(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public int getRowCount() {
		if (clients == null) {
			return 0;
		} else {
			return clients.size();
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
			return (int) clients.get(row).getId();
		case 1:
			return (String) clients.get(row).getDni();
		case 2:
			return (String) clients.get(row).getName();
		case 3:
			return (String) clients.get(row).getSurnames();
		default:
			return null;
		}
		
	}
	


	public Client getClient(int row) {
		Client client = new Client();
		int id = (int) getValueAt(row,0);
		String dni = (String) getValueAt(row,1);
		String name = (String) getValueAt(row,2);
		String surnames = (String) getValueAt(row,3);
		client.setId(id);
		client.setDni(dni);
		client.setName(name);
		client.setSurnames(surnames);
		return client;
	}
	
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
	}

}
