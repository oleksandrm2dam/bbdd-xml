package control;

import java.util.ArrayList;
import java.util.Arrays;

import org.basex.core.BaseXException;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import entities.Warehouse;
import main.mainBook;

public class WarehouseControl {
	
	public Warehouse selectWarehouseCod(String cod) {
		Warehouse warehouse = new Warehouse();
		QueryProcessor qp = null;
		try {
			String query = "for $i in doc('DBExample')//warehouses/warehouse " + "where $i/codWarehouse = '" + cod + "' "
					+ "let $array := array{(data($i/codWarehouse), data($i/name), data($i/population), data($i/telephone), data($i/email))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				loadWarehouse(warehouse, item);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		return warehouse;
	}
	
	public ArrayList<Warehouse> selectWarehouse(String field, String value) {
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//warehouses/warehouse " + "where " + field + " = '" + value + "' "
					+ "let $array := array{(data($i/codWarehouse), data($i/name), data($i/population), data($i/telephone), data($i/email))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Warehouse warehouse = new Warehouse();
				loadWarehouse(warehouse, item);
				warehouses.add(warehouse);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return warehouses;
	}
	
	public ArrayList<Warehouse> selectAllWarehouses() {
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//warehouses/warehouse "
					+ "let $array := array{(data($i/codWarehouse), data($i/name), data($i/population), data($i/telephone), data($i/email))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Warehouse warehouse = new Warehouse();
				loadWarehouse(warehouse, item);
				warehouses.add(warehouse);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return warehouses;
	}
	
	public void insertWarehouse(Warehouse warehouse) {
		try {
			String query = "let $up := <warehouse>" + "<codWarehouse>" + warehouse.getCodWarehouse() + "</warehouse>" + " <name>"
					+ warehouse.getName() + "</name>" + "<population>" + warehouse.getPopulation() + "</population>" 
					+ "<telephone>" + warehouse.getTelephone() + "</telephone>"
					+ "<email>" + warehouse.getEmail() + "</email>"
					+ " </warehouse> " + "return insert node $up as last into doc('DBExample')//warehouses ";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteWarehouse(String codWarehouse) {
		try {
			String query = "for $i in doc('DBExample')//warehouses/warehouse where $i/codWarehouse = '" + codWarehouse + "' "
					+ " return delete node $i";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void updateWarehouse(Warehouse warehouse) {
		try {
			String query = "let $up := <warehouse>" 
					+ "<codWarehouse>" + warehouse.getCodWarehouse() + "</warehouse>" 
					+ " <name>" + warehouse.getName() + "</name>" 
					+ "<population>" + warehouse.getPopulation() + "</population>" 
					+ "<telephone>" + warehouse.getTelephone() + "</telephone>"
					+ "<email>" + warehouse.getEmail() + "</email>"
					+ " </warehouse> " 
					+ "for $i in doc('DBExample')//warehouses/warehouse where $i/codWarehouse = '" 
					+ warehouse.getCodWarehouse() + "' "
					+ " return replace node $i with $up";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void loadWarehouse(Warehouse warehouse, Item item) {
		try {
			Object[] objArr = (Object[]) item.toJava();
			String[] strArr = Arrays.copyOf(objArr, objArr.length, String[].class);
			warehouse.setCodWarehouse(Integer.parseInt(strArr[0]));
			warehouse.setName(strArr[1]);
			warehouse.setPopulation(strArr[2]);
			warehouse.setTelephone(strArr[3]);
			warehouse.setEmail(strArr[4]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
