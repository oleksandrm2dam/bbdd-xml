package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.Warehouse;

public class WarehouseTblModel extends AbstractTableModel {

	private String[] columnNames = { "ID", "NAME", "POPULATION", "TELEPHONE", "EMAIL"};
	ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	
	public WarehouseTblModel() {

	}

	public WarehouseTblModel(ArrayList<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public int getRowCount() {
		if (warehouses == null) {
			return 0;
		} else {
			return warehouses.size();
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
			return warehouses.get(row).getCodWarehouse();
		case 1:
			return warehouses.get(row).getName();
		case 2:
			return warehouses.get(row).getPopulation();
		case 3:
			return warehouses.get(row).getTelephone();
		case 4:
			return warehouses.get(row).getEmail();
		default:
			return null;
		}
		
	}
	
	public Warehouse getWarehouse(int row) {
		Warehouse warehouse = new Warehouse();
		int codWarehouse = (int) getValueAt(row,0);
		String name = (String) getValueAt(row,1);
		String population = (String) getValueAt(row,2);
		String telephone = (String) getValueAt(row,3);
		String email = (String) getValueAt(row,4);
		warehouse.setCodWarehouse(codWarehouse);
		warehouse.setName(name);
		warehouse.setPopulation(population);
		warehouse.setTelephone(telephone);
		warehouse.setEmail(email);
		return warehouse;
	}
	
	public void setWarehouses(ArrayList<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public void addWarehouse(Warehouse warehouse) {
		this.warehouses.add(warehouse);
	}
}
