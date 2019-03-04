package control;

import java.util.ArrayList;
import java.util.Arrays;

import org.basex.core.BaseXException;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;

import entities.Stock;
import main.mainBook;

public class StockControl {

	public Stock selectStockIsbnCod(String isbn, int codWarehouse) {
		Stock stock = new Stock();
		QueryProcessor qp = null;
		try {
			String query = "for $i in doc('DBExample')//stocks/stock " + "where $i/isbn = '" + isbn + "' "
					+ "and $i/codWarehouse = '" + codWarehouse + "' "
					+ "let $array := array{(data($i/isbn), data($i/codWarehouse), data($i/quantity))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				loadStock(stock, item);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		return stock;
	}
	
	public ArrayList<Stock> selectStock(String field, String value) {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//stocks/stock " + "where " + field + " = '" + value + "' "
					+ "let $array := array{(data($i/isbn), data($i/codWarehouse), data($i/quantity))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Stock stock = new Stock();
				loadStock(stock, item);
				stocks.add(stock);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return stocks;
	}
	
	public ArrayList<Stock> selectAllStocks() {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		QueryProcessor qp = null;
		
		try {
			String query = "for $i in doc('DBExample')//stocks/stock "
					+ "let $array := array{(data($i/isbn), data($i/codWarehouse), data($i/quantity))}"
					+ "return $array";
			qp = new QueryProcessor(query, mainBook.context);
			Iter iter = qp.iter();
			Item item;
			while((item = iter.next()) != null) {
				Stock stock = new Stock();
				loadStock(stock, item);
				stocks.add(stock);
			}
		} catch (QueryException e) {
			e.printStackTrace();
		} finally {
			if(qp != null) {
				qp.close();
			}
		}
		
		return stocks;
	}
	
	public void insertStock(Stock stock) {
		try {
			String query = "let $up := <stock>" 
					+ "<isbn>" + stock.getIsbn() + "</isbn>" 
					+ "<codWarehouse>" + stock.getCodWarehouse() + "</codWarehouse>" 
					+ "<quantity>" + stock.getQuantity() + "</quantity>"
					+ " </stock> " 
					+ "return insert node $up as last into doc('DBExample')//stocks ";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStock(String isbn, int codWarehouse) {
		try {
			String query = "for $i in doc('DBExample')//stocks/stock where $i/isbn = '" + isbn + "' "
					+ "and $i/codWarehouse = '" + codWarehouse + "' "
					+ " return delete node $i";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStock(Stock stock) {
		try {
			String query = "let $up := <stock>" 
					+ "<isbn>" + stock.getIsbn() + "</isbn>" 
					+ "<codWarehouse>" + stock.getCodWarehouse() + "</codWarehouse>" 
					+ "<quantity>" + stock.getQuantity() + "</quantity>"
					+ " </stock> " 
					+ "for $i in doc('DBExample')//stocks/stock where $i/isbn = '" + stock.getIsbn() + "' "
					+ "and $i/codWarehouse = '" + stock.getCodWarehouse() + "' "
					+ " return replace node $i with $up";
			new XQuery(query).execute(mainBook.context);
		} catch (BaseXException e) {
			e.printStackTrace();
		}
	}
	
	public void loadStock(Stock stock, Item item) {
		try {
			Object[] objArr = (Object[]) item.toJava();
			String[] strArr = Arrays.copyOf(objArr, objArr.length, String[].class);
			stock.setIsbn((strArr[0]));
			stock.setCodWarehouse(Integer.parseInt(strArr[1]));
			stock.setQuantity(Integer.parseInt(strArr[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
