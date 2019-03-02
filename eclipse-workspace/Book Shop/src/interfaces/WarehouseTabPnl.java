package gui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import entities.Stock;
import entities.Warehouse;

public class WarehouseTabPnl extends TabPnl{

	private ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	
	public WarehouseTabPnl() {
		super();
		initWarehouse();
	}
	
	@Override
	public void deleteAction() {
		int[] rows = table.getSelectedRows();
		delete(rows, warehouses);
	}

	@Override
	public void textFieldSearchAction() {
		String value = textField.getText();
		switch((String)comboBox.getSelectedItem()) {
		case "CODE": //searchWarehouseCode
			break;
		case "NAME": //searchWarehouseName
			break;
		case "POPULATION": //searchWarehousePopulation
			break;
		}
		((WarehouseTblModel)table.getModel()).setWarehouses(warehouses);
		table.clearSelection();
		table.repaint();
		
	}

	@Override
	public void addAction() {
		WarehouseDialog add = new WarehouseDialog();
		add.setVisible(true);
		
	}

	@Override
	public void editAction() {
		int row = table.getSelectedRow();
		Warehouse warehouse = new Warehouse();
		if(row>=0) {
			warehouse = ((WarehouseTblModel)table.getModel()).getWarehouse(row);
		}
		WarehouseDialog edit = new WarehouseDialog(warehouse);
		edit.setVisible(true);
	}


	public void initWarehouse(){
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "CODE", "NAME", "POPULATION"}));
		table.setModel(new WarehouseTblModel());
		scrollPane.setViewportView(table);
	}
}
